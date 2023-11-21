$(document).ready(function() {
    $('body').hide().slideDown(1000, function() {
        $(this).fadeIn(1000);
    });
    $('body').css('opacity', '1');
});
