$(document).ready(function() {
  const color_theme = localStorage.getItem('color_theme');
  if (color_theme === 'dark') {
    $(':root').addClass('dark');
    $('.darkMode').addClass('active');
  } else {
    $(':root').removeClass('dark');
    $('.darkMode').removeClass('active');
  }
});

$('.darkMode').click(function() {
  const color_theme = localStorage.getItem('color_theme');
  if (color_theme === 'dark') {
    $(':root').removeClass('dark');
    $('.darkMode').removeClass('active');
    localStorage.setItem('color_theme', 'light');
  } else {
    $(':root').addClass('dark');
    $('.darkMode').addClass('active');
    localStorage.setItem('color_theme', 'dark');
  }
});