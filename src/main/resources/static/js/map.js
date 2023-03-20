let map;
let markers;

// Map functions
function create_map(div_name) {
    var mapProp= {
        center:new google.maps.LatLng(51.508742,-0.120850),
        zoom:5,
    };
    map = new google.maps.Map(document.getElementById(div_name),mapProp);
    return map;
}

// Marker functions
function create_marker(x, y, name, color, map) {
    //x = Math.random()*10+40;
    //y = Math.random()*6+2;
    var LatLng = {lat: x, lng: y};
    console.log(color);
    marker = new google.maps.Marker({
        position: LatLng,
        title: name,
        icon: {url: "http://maps.google.com/mapfiles/ms/icons/"+color+"-dot.png"},
    });
    marker.setMap(map);
    marker.setVisible(false);
    return marker;
}

function add_marker(x, y, map) {
    var LatLng = {lat: x, lng: y};
    markers = new google.maps.Marker({position: LatLng});
    markers.setMap(map);
}

function initMap() {
    alert("Hello World");
    create_map("map");
    add_marker(50, 10, map);		
}