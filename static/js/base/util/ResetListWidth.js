/**
 * Created by Shin on 2015/10/26.
 */
function resetWidth(){
    var h = $(".datacontent");
    var width = h.outerWidth( true );
    //console.log(width);
    $(".listtitle").css('width', width);
    //alert($(".listtitle").html());
}