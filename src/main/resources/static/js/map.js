const mapDiv = document.getElementById('map');

// 현재 위치 파악
navigator.geolocation.getCurrentPosition(success, fail);

// 위치 정보 수락 시
function success(pos) {
    let zoom = 15;
    const lat = pos.coords.latitude;
    const lng = pos.coords.longitude;

    loadNaverMap(lat, lng, zoom);
}

// 위치 정보 거부 시
function fail(err) {
    loadNaverMap(37.3595704, 127.105399, 15);
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

    const map = new naver.maps.Map(mapDiv, mapOptions);

    const markerOptions = {
        position: position.destinationPoint(90, 1),
        map: map,
        icon: {
            url: '/icon/currentPosition30.png'
        }
    }

    var marker = new naver.maps.Marker(markerOptions);
}
