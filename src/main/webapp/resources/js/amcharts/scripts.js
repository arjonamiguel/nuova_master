

    // Barra slider
    $(document).ready(function () {

        $("#ex1").slider({ min: 0, max: 10000, value: 0, focus: true });
       
        $("#ex1Slider").change(function () {

            var titulo = $("#ex1Slider .slider-handle").attr("aria-valuenow");


            var myArray = ['', 'ay', 'uff', 'Auuuu', '\u00A1\u00A1#***@\u0021\u0021'];


            $("#inver").val($("#ex1").val());
        });
    });


