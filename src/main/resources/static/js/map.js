const mapDiv = document.getElementById('map');
let map;

// 현재 위치 파악
navigator.geolocation.getCurrentPosition(success, fail);

// 위치 정보 수락 시
function success(pos) {
    let zoom = 15;
    const lat = pos.coords.latitude;
    const lng = pos.coords.longitude;

    loadNaverMap(lat, lng, zoom);
    updateToilet();
}

// 위치 정보 거부 시
function fail(err) {
    loadNaverMap(37.3595704, 127.105399, 15);
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
}

function updateToilet() {
    const bounds = map.getBounds();
    const range = {
        swLat: bounds._sw._lat,
        neLat: bounds._ne._lat,
        swLng: bounds._sw._lng,
        neLng: bounds._ne._lng
    }

    $.ajax({
        url: "/api/toilets",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(range),
        success: function(res) {
           console.log(res);
        },
        error: function(req, status, error) {
           console.log(error);
        }
    });
}
