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

  // 팝업 창 표시
  function showTimeSelectionModal(selectedDate) {
    var modal = document.getElementById("timeSelectionModal");
    var dateDisplay = document.getElementById("selectedDate");
    var timeButtonsContainer = document.getElementById("timeButtons");

    dateDisplay.textContent = "날짜 : " + selectedDate;

    // 버튼 초기화
    timeButtonsContainer.innerHTML = "";

    // 10시부터 19시까지 1시간 단위 버튼 생성
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

  // 팝업 창 닫기
  function closeModal() {
    var modal = document.getElementById("timeSelectionModal");
    modal.style.display = "none";
  }

  // 시간 선택 이벤트
  function selectTime(selectedDate, selectedTime) {
    confirm(`예약하시겠습니까?`);
    closeModal();
  }