$(function(){
 
  function formSwitch(e) {
    var self = $(this),
        form = $('form');
    
    if(form.hasClass('back-visible')) 
    {
       self.siblings($('h2')).text('Нет аккаунта?');
       self.text('Регистрация!');
       form.removeClass('back-visible');
    } else {
      self.siblings($('h2')).text('Уже зарегистрированы?');
      self.text('Войти!');
      form.addClass('back-visible');
    }
    
    e.preventDefault();
  }
  
  $('#form-switch').on('click', formSwitch);
  
  $('form').submit(function(e){
    e.preventDefault();
  });
});