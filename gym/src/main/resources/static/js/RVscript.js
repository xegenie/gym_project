document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calendar");

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    dateClick: function (info) {
      // 날짜 클릭 시 실행
      showTimeSelectionModal(info.dateStr);
    },
  });

  calendar.render();
});

function showTimeSelectionModal(selectedDate) {
  var modal = document.getElementById("timeSelectionModal");
  var dateDisplay = document.getElementById("selectedDate");
  var timeButtonsContainer = document.getElementById("timeButtons");

  dateDisplay.textContent = "날짜 : " + selectedDate;

  timeButtonsContainer.innerHTML = "";

  for (let hour = 10; hour <= 21; hour++) {
    let button = document.createElement("button");
    button.textContent = `${hour}:00`;
    button.onclick = function () {
      selectTime(selectedDate, `${hour}:00`);
    };
    button.style.margin = "5px";
    button.style.padding = "10px 20px";
    button.style.cursor = "pointer";
    timeButtonsContainer.appendChild(button);
  }

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
  const rvDate = new Date(`${selectedDate}T${selectedTime}:00`);

  document.getElementById("rvDateInput").value = rvDate.toISOString();
  document.getElementById("reservationForm").submit();
}