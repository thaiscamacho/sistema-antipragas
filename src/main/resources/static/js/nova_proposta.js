$(function(){
    var atual_fs, next_fs, prev_fs;
    var formulario = $('form[name=form-proposta]');

    //função para passar para o próximo passo
    function next(elem){
        atual_fs = $(elem).parent();
        next_fs = $(elem).parent().next();

        $('#progress li').eq($('fieldset').index(next_fs)).addClass('ativo')
        atual_fs.hide(800);
        next_fs.show(800);
    }

    //função para voltar um passo
    $('.prev').click(function(){
        atual_fs = $(this).parent();
        prev_fs = $(this).parent().prev();

        $('#progress li').eq($('fieldset').index(atual_fs)).removeClass('ativo');
        atual_fs.hide(800);
        prev_fs.show(800);
    });

    $('input[name=next1]').click(function(){
        var campos = formulario.serializeArray();

        if(campos[1].value > 1){
            if(campos[3].value == 'FREQUENCIA_NULA'){
                $('.error-frequencia').html('<p class="error-frequencia"><span style="color: red">Informe uma frequência</span></p>');
                return;
            }else{
                $('.error-frequencia').html('<p class="error-frequencia"></p>');
            }
        }else{
            $('.error-frequencia').html('<p class="error-frequencia"></p>');
        }

        next($(this));
    });

    $('input[type=submit]').click(function (evento) {
        var campos = formulario.serializeArray();

        if(campos[4].value == "") {
            $('.error-praga').html('<p class="error-praga"><span style="color: red">Informe pelo menos um endereço</span></p>');
            return false;
        }else{
            $('.error-praga').html('<p class="error-praga"></p>');
            evento.submit();
        }
    });

    //só deixa escolher uma freq se visita > 1
    $('.qtdVisita').click(function () {
       if(this.value > 1){
           document.getElementById("frequencia").style.display="block";
       }else{
           document.getElementById("frequencia").style.display="none";
           document.getElementById("freq_nula").checked = "checked";
       }
    });

});