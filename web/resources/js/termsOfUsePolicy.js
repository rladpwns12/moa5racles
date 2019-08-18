function change1() {
    $('#user').show();
    $('#host').hide();
    $('#service').hide();

    $('#one').attr('class', 't_l on');
    $('#two').removeClass('on');
    $('#three').removeClass('on');
}

function change2() {
    $('#user').hide();
    $('#host').show();
    $('#service').hide();


    $('#one').removeClass('on');
    $('#two').attr('class', 't_l on');
    $('#three').removeClass('on');
}

function change3() {
    $('#user').hide();
    $('#host').hide();
    $('#service').show();

    $('#one').removeClass('on');
    $('#two').removeClass('on');
    $('#three').attr('class', 't_l on');
}