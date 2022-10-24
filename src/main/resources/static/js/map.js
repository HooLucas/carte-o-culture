<script>
    let map;
    let markers;

    // Map functions
    function create_map(div_name) {
        var mapProp= {
            center:new google.maps.LatLng(51.508742,-0.120850),
            zoom:5,
        };
        map = new google.maps.Map(document.getElementById("map"),mapProp);
        return map;
    }

    // Marker functions
    function add_marker(x, y, map) {
        var LatLng = {lat: x, lng: y};
        markers = new google.maps.Marker({position: LatLng});
        markers.setMap(map);
    }

    function initMap() {
        create_map("map");
        add_marker(50, 10, map);		
    }
</script>