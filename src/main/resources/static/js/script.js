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
  loop: true,
  speed: 1000,
  allowTouchMove: true,
  autoplay: {
    delay: 3000, // Un poco más de tiempo para que no sea tan rápido
    disableOnInteraction: false,
  },
  breakpoints: {
    0: { slidesPerView: 1, spaceBetween: 20 },
    576: { slidesPerView: 2, spaceBetween: 20 },
    992: { slidesPerView: 3, spaceBetween: 30 }
  }
});


// validacion de contraseñas

document.addEventListener('DOMContentLoaded', function () {
  const form = document.querySelector('form[th\\:action="@{/reset-password}"], form[action="/reset-password"]');
  if (!form) return;
  form.addEventListener('submit', function (e) {
    const password = form.newPassword.value;
    const confirm = form.confirmPassword.value;
    const errorDiv = form.querySelector('.form-error') || document.createElement('div');
    errorDiv.className = 'form-error text-danger mt-2';

    // Validación: al menos 6 caracteres, una letra y un número
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
    if (!regex.test(password)) {
      e.preventDefault();
      errorDiv.textContent = 'La contraseña debe tener al menos 6 caracteres, incluir letras y al menos un número.';
      form.appendChild(errorDiv);
      return false;
    }
    if (password !== confirm) {
      e.preventDefault();
      errorDiv.textContent = 'Las contraseñas no coinciden.';
      form.appendChild(errorDiv);
      return false;
    }
    errorDiv.textContent = '';
  });
});
// banner cookies
document.addEventListener('DOMContentLoaded', function() {
    var cookieBanner = document.getElementById('cookie-banner');
    var acceptBtn = document.getElementById('accept-cookies');
    var rejectBtn = document.getElementById('reject-cookies');

    // Comprueba si ya hay consentimiento
    if (!localStorage.getItem('cookieConsent')) {
        cookieBanner.style.display = 'block';
    }

    acceptBtn.onclick = function() {
        localStorage.setItem('cookieConsent', 'accepted');
        cookieBanner.style.display = 'none';
        // Aquí puedes inicializar cookies/analítica si lo necesitas
    };

    rejectBtn.onclick = function() {
        localStorage.setItem('cookieConsent', 'rejected');
        cookieBanner.style.display = 'none';
        // Aquí puedes bloquear cookies/analítica si lo necesitas
    };
});


