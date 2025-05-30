// Activa todos los popovers y tooltips de la página cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', function () {
  // Popovers de schedule.html
  let popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
  popoverTriggerList.forEach(function (popoverTriggerEl) {
    new bootstrap.Popover(popoverTriggerEl);
  });
  // Tooltips de formularios
  let tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl);
  });
});
// swiper de index.html
const swiper = new Swiper(".mySwiper", {
  slidesPerView: 3,
  spaceBetween: 20,
  loop: true,
  autoplay: {
    delay: 1, // Intervalo mínimo entre slides (en ms)
    disableOnInteraction: false,
  },
  speed: 2500, // Duración de la transición (en ms)
  allowTouchMove: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: false,
    enabled: false // Oculta la paginación
  },
  breakpoints: {
    0: { slidesPerView: 1 },
    576: { slidesPerView: 2 },
    992: { slidesPerView: 3 }
  }
});