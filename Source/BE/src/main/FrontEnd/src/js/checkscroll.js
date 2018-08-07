/*
* This function is created to control the sticky-footer
* Stiky-footer will transparent when scroll
*/
function checkScroll(){
    if($(window).scrollTop() > 0){
        $('.sticky-footer').addClass("scrolled");
    }
    setTimeout(function() {
        $('.sticky-footer').removeClass("scrolled");
    }, 500);
}

if($('.well_place').length > 0){
    $(window).on("scroll load resize", function(){
        checkScroll();
    });
} else {
}