document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calendar");

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    dateClick: function (info) {
      showTimeSelectionModal(info.dateStr);
    },
  });

  calendar.render();
});

function showTimeSelectionModal(selectedDate) {
  var modal = document.getElementById("timeSelectionModal");
  var dateDisplay = document.getElementById("selectedDate");

  dateDisplay.textContent = `${selectedDate}`;


  modal.style.display = "block";
}

function closeModal() {
  var modal = document.getElementById("timeSelectionModal");
  modal.style.display = "none";
}

function selectTime(selectedDate, selectedTime) {
  if (confirm(`${selectedDate} ${selectedTime} 예약하시겠습니까?`)) {
    submitReservation(selectedDate, selectedTime);
  }
}

function submitReservation(selectedDate, selectedTime) {
  const [year, month, day] = selectedDate.split("-").map(Number);
  const [hour, minute] = selectedTime.split(":").map(Number);

  const rvDate = new Date(year, month - 1, day, hour, minute);

  const formattedDate = formatDate(rvDate);

  document.getElementById("rvDateInput").value = formattedDate;

  document.getElementById("reservationForm").submit();
}

function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hour = String(date.getHours()).padStart(2, '0');
  const minute = String(date.getMinutes()).padStart(2, '0');
  const second = String(date.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
}

const trainerList = document.getElementById("trainerList")
trainerList.addEventListener("change", () => {
  const selectedTrainer = trainerList.value;
})