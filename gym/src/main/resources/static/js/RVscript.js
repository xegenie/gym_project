document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calendar");

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    dateClick: function (info) {
      // 날짜 클릭 시 모달 창을 띄우고 시간 선택 기능 제공
      showTimeSelectionModal(info.dateStr);
    },
  });

  calendar.render();
});

function showTimeSelectionModal(selectedDate) {
  var modal = document.getElementById("timeSelectionModal");
  var dateDisplay = document.getElementById("selectedDate");
  var timeButtonsContainer = document.getElementById("timeButtons");

  // 모달에 선택된 날짜 표시
  dateDisplay.textContent = "날짜 : " + selectedDate;

  // 시간 선택 버튼 동적으로 생성
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

  // ISO-8601 형식으로 변환하여 hidden input에 삽입
  document.getElementById("rvDateInput").value = rvDate.toISOString();

  // form 제출
  document.getElementById("reservationForm").submit();
}
