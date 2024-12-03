const monthNames = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];

  let currentDate = new Date();

  function renderCalendar(date) {
    const year = date.getFullYear();
    const month = date.getMonth();

    // 업데이트할 요소
    const monthElement = document.querySelector(".current-month");
    const yearElement = document.querySelector(".year");
    const datesElement = document.querySelector(".dates");

    // 월과 연도 설정
    monthElement.textContent = monthNames[month];
    yearElement.textContent = year;

    // 해당 월의 날짜 계산
    const firstDayOfMonth = new Date(year, month, 1).getDay(); // 월 첫 번째 날 요일
    const lastDateOfMonth = new Date(year, month + 1, 0).getDate(); // 월 마지막 날짜
    const lastDayOfLastMonth = new Date(year, month, 0).getDate(); // 이전 달 마지막 날짜

    // 캘린더 초기화
    datesElement.innerHTML = "";

    // 이전 달의 날짜 채우기
    const startDay = (firstDayOfMonth === 0 ? 7 : firstDayOfMonth) - 1;
    for (let i = startDay; i > 0; i--) {
      const btn = document.createElement("button");
      btn.textContent = lastDayOfLastMonth - i + 1;
      btn.style.opacity = "0.5"; // 비활성화 효과
      datesElement.appendChild(btn);
    }

    // 이번 달 날짜 채우기
    const today = new Date();
    for (let i = 1; i <= lastDateOfMonth; i++) {
      const btn = document.createElement("button");
      btn.innerHTML = `<time>${i}</time>`;

      // 오늘 날짜 강조
      if (
        year === today.getFullYear() &&
        month === today.getMonth() &&
        i === today.getDate()
      ) {
        btn.classList.add("today");
      }
      datesElement.appendChild(btn);
    }

    // 다음 달 날짜 채우기 (캘린더 채우기 용)
    const remainingCells = 42 - datesElement.children.length; // 6행 * 7열
    for (let i = 1; i <= remainingCells; i++) {
      const btn = document.createElement("button");
      btn.textContent = i;
      btn.style.opacity = "0.5"; // 비활성화 효과
      datesElement.appendChild(btn);
    }
  }

  // 이전/다음 버튼 이벤트 추가
  document.querySelector(".prev").addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
  });

  document.querySelector(".next").addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
  });

  // 초기 렌더링
  renderCalendar(currentDate);