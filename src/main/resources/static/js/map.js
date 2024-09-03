const mapDiv = document.getElementById('map');
const zoom = 16;
const searchBtn = $('#searchBtn');

let map;
let markers = new Array();
let infoWindows = new Array();
let currentBounds;

// 현재 위치 파악
navigator.geolocation.getCurrentPosition(success, fail);

// 위치 정보 수락 시
function success(pos) {
    const lat = pos.coords.latitude;
    const lng = pos.coords.longitude;

    loadNaverMap(lat, lng, zoom);
    updateToilet();
}

// 위치 정보 거부 시
function fail(err) {
    loadNaverMap(37.3595704, 127.105399, zoom);
    updateToilet();
}

function loadNaverMap(lat, lng, zoom) {
    const position = new naver.maps.LatLng(lat, lng);
    const mapOptions = {
        center: position,
        zoom: zoom,
        minZoom: 7,                                 //지도의 최소 줌 레벨
        zoomControl: true,                          //줌 컨트롤의 표시 여부
        zoomControlOptions: {                       //줌 컨트롤의 옵션
            position: naver.maps.Position.TOP_RIGHT
        },
        tileTransition: true,                       // 타일 fadeIn 효과
        scaleControl: false,
        logoControl: true,
        mapDataControl: false,
        mapTypeControl: true
    }

    map = new naver.maps.Map(mapDiv, mapOptions);

    // 지도를 움직이면 '현 위치에서 찾기' 표출
    naver.maps.Event.addListener(map, 'idle', function() {
        const newBounds = map.getBounds();

        if (!currentBounds.equals(newBounds)) {
            searchBtn.show(); // 범위가 변경되면 버튼 표시
        } else {
            searchBtn.hide(); // 범위가 동일하면 버튼 숨김
        }
    });
}

function updateToilet() {
    const bounds = map.getBounds();
    currentBounds = bounds;

    const range = {
        swLat: bounds._sw._lat,
        neLat: bounds._ne._lat,
        swLng: bounds._sw._lng,
        neLng: bounds._ne._lng
    }

    // 기존 마커 제거
    removeAllMarkers();

    // 서버에서 데이터 가져오기
    $.ajax({
        url: "/api/toilets",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(range),
        success: function(res) {
           for (let i=0; i<res.length; i++) {
               const marker = new naver.maps.Marker({
                  map: map,
                  title: res[i].toiletName,
                  position: new naver.maps.LatLng(res[i].latitude, res[i].longitude)
               });

               const infoWindow = new naver.maps.InfoWindow({
                  content: '<div style="width:200px;text-align: center;padding: 10px"><b>' + res[i].toiletName +
                      '</b></br>' + res[i].roadName + '</div>'
               });
               markers.push(marker);
               infoWindows.push(infoWindow);
           }

            for (let i=0; i<markers.length; i++) {
                naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
            }
        },
        error: function(req, status, error) {
           console.log(error);
        }
    });
}

// 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
function getClickHandler(seq) {
    return function (e) {
        const marker = markers[seq],
            infoWindow = infoWindows[seq];

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker);
        }
    }
}

// 모든 마커 제거 함수
function removeAllMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null); // 지도에서 마커 제거
    }
    markers = [];

    for (let i = 0; i < infoWindows.length; i++) {
        infoWindows[i].close();
    }
    infoWindows = [];
}

searchBtn.on('click', function() {
    updateToilet();
    searchBtn.hide();
});
