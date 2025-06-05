document.addEventListener('DOMContentLoaded', function () {
  // Popovers
  let popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
  popoverTriggerList.forEach(function (el) {
    new bootstrap.Popover(el);
  });

  // Tooltips
  let tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.forEach(function (el) {
    new bootstrap.Tooltip(el);
  });

  // Toggle de visibilidad de contraseñas
  document.querySelectorAll('.toggle-password').forEach(function (btn) {
    btn.addEventListener('click', function (e) {
      e.preventDefault();
      const targetId = btn.getAttribute('data-target');
      const input = document.getElementById(targetId);
      const icon = btn.querySelector('i');
      if (input) {
        const type = input.type === 'password' ? 'text' : 'password';
        input.type = type;
        icon.classList.toggle('bi-eye');
        icon.classList.toggle('bi-eye-slash');
      }
    });
  });
});
// Validación de contraseña
const form = document.querySelector('form[th\\:action="@{/reset-password}"], form[action="/reset-password"]');
if (form) {
  form.addEventListener('submit', function (e) {
    const password = form.newPassword.value;
    const confirm = form.confirmPassword.value;
    const errorDiv = form.querySelector('.form-error') || document.createElement('div');
    errorDiv.className = 'form-error text-danger mt-2';

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
}
// Banner cookies
const cookieBanner = document.getElementById('cookie-banner');
const acceptBtn = document.getElementById('accept-cookies');
const rejectBtn = document.getElementById('reject-cookies');

if (cookieBanner && acceptBtn && rejectBtn) {
  if (!localStorage.getItem('cookieConsent')) {
    cookieBanner.style.display = 'block';
  }

  acceptBtn.onclick = function () {
    localStorage.setItem('cookieConsent', 'accepted');
    cookieBanner.style.display = 'none';
  };

  rejectBtn.onclick = function () {
    localStorage.setItem('cookieConsent', 'rejected');
    cookieBanner.style.display = 'none';
  };
}
  // Swiper
  const swiper = new Swiper(".mySwiper", {
    loop: true,
    freeMode: true,
    speed: 2000,
    autoplay: {
      delay: 0,
      disableOnInteraction: false,
    },
    slidesPerView: "auto",
    spaceBetween: 30,
  });



