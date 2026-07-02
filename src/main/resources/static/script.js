const form = document.querySelector("#contactForm");
const statusEl = document.querySelector("#formStatus");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  statusEl.className = "form-status";
  statusEl.textContent = "Отправляем...";

  const formData = new FormData(form);
  const payload = {
    name: formData.get("name").trim(),
    phone: formData.get("phone").trim(),
    message: formData.get("message").trim()
  };

  try {
    const response = await fetch("/api/contact", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(payload)
    });

    const data = await response.json();

    if (!response.ok) {
      const fieldErrors = data.fieldErrors || {};
      throw new Error(fieldErrors.phone || fieldErrors.name || fieldErrors.message || data.message || "Проверьте данные в форме.");
    }

    statusEl.classList.add("success");
    statusEl.textContent = data.message || "Заявка отправлена.";
    form.reset();
  } catch (error) {
    statusEl.classList.add("error");
    statusEl.textContent = error.message || "Не получилось отправить. Позвоните по номеру выше.";
  }
});
