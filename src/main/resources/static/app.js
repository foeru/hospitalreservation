const API_URL = "/api/reservations";

// 예약 가능한 시간 버튼 생성
async function createTimeButtons(date, hospitalId) {
    const timeButtonsContainer = document.getElementById("time-buttons");
    timeButtonsContainer.innerHTML = ""; // 기존 버튼 초기화

    // 예약 가능한 시간대
    const availableTimes = [
        "09:00", "10:00", "11:00", "12:00", "13:00",
        "14:00", "15:00", "16:00", "17:00"
    ];

    try {
        const response = await fetch(`${API_URL}?date=${date}&hospitalId=${hospitalId}`);
        const reservedTimes = await response.json(); // 이미 예약된 시간대 가져오기

        availableTimes.forEach(time => {
            const button = document.createElement("button");
            button.textContent = time;
            button.value = time;

            // 이미 예약된 시간이라면 비활성화
            if (reservedTimes.includes(time)) {
                button.disabled = true;
                button.classList.add("disabled");
            }

            // 버튼 클릭 이벤트 추가
            button.addEventListener("click", async () => {
                await addReservation(hospitalId, time, date);
            });

            timeButtonsContainer.appendChild(button);
        });

    } catch (error) {
        console.error("Error loading time buttons:", error);
    }
}

// 예약 추가 함수
async function addReservation(hospitalId, time, date) {
    const patientId = document.getElementById("patient-id").value;
    const reservationTime = `${date}T${time}:00`;

    const reservationData = {
        hospitalId,
        patientId,
        reservationTime,
        status: "예약완료"
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(reservationData)
        });

        if (response.ok) {
            document.getElementById("response-message").textContent = "예약 성공!";
            // 시간 버튼 새로고침 (예약 완료된 시간을 비활성화)
            await createTimeButtons(date, hospitalId);
        } else {
            const errorData = await response.json();
            document.getElementById("response-message").textContent = `예약 실패: ${errorData.error}`;
        }
    } catch (error) {
        console.error("Error adding reservation:", error);
        document.getElementById("response-message").textContent = "예약 실패: 네트워크 오류";
    }
}

// 날짜 또는 병원 ID 변경 시 이벤트
document.getElementById("reservation-date").addEventListener("change", async function () {
    const hospitalId = document.getElementById("hospital-id").value;
    const date = document.getElementById("reservation-date").value;

    if (hospitalId && date) {
        await createTimeButtons(date, hospitalId);
    }
});

// 오늘 날짜를 YYYY-MM-DD 형식으로 가져오는 함수
function getTodayDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

// 날짜 필드의 min 값 설정
document.addEventListener('DOMContentLoaded', () => {
    const dateInput = document.getElementById('reservation-date');
    dateInput.min = getTodayDate(); // 오늘 날짜 설정
});