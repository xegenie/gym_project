# 🏋️ **프로젝트 : FIT NEXUS**
![image](https://github.com/user-attachments/assets/2464dcb2-9abd-4f0b-9576-dbbd4de6fb46)

---

## 🎥 **프로젝트 발표 영상**  
[**발표 영상 링크**](https://www.youtube.com/watch?v=HG9iWUQFf-4)



---

## 📚 **프로젝트 소개**
- **주제**
    - 스마트 헬스장 통합관리 시스템
    
- **선정 배경**
    - 현대인의 건강과 운동에 대한 관심이 높아짐에 따라 헬스장은 필수적인 생활공간이며 이용자가 더욱 편리하게 헬스장을 이용할 수 있도록 하기 위함
    
- **기획 의도**
    - 기존 헬스장 시스템에 존재하는 **헬스장 등록, PT 예약, 실시간 현황 파악 등의** 어려움을 개선하기 위해 기획하게 되었음
    
- **활용방안**
    - QR 코드를 통한 출입 관리 시스템
    - 원하는 날짜에 본인이 선택하는 PT 예약 시스템
    - 실시간으로 헬스장의 혼잡도 파악
    - 본인만의 스케줄을 계획하는 일정관리
    
- **기대효과**
    - 불필요한 시간을 절감
    - 헬스장 이용 편리성 증대
    - 개인 맞춤 서비스를 강화
    - 동기 부여 형성과 고객 유지율 상승
    - 매출 관리 및 운영 효율성 증대

---

&nbsp;
# 🎯 **주요 기능**
## ✅ 회원가입 및 로그인/로그아웃  
## ✅ 실시간 헬스장 이용자 수 확인  
## ✅ QR코드 기반 출입 관리  
## ✅ PT 예약 및 트레이너 스케줄 관리  
## ✅ 회원 정보 수정 및 권한 관리  
## ✅ 관리자 페이지를 통한 회원 및 예약 관리  
## ✅ 이용 통계 제공  

--- 

## 👥 **팀 구성**
| **이름**   | **담당 기능**                       |
|-----------|----------------------------------|
| **오승원** | 메인화면구성, 출석체크(QR), 출석내역관리, 출석랭킹조회, 이용권내역조회 |
| **이세진** | 이용권 관리, 트레이너 프로필 관리, 결제API, 구매내역 관리, 매출 관리 |
| **홍성윤** | 예약기능, 캘린더API연동, 관리자 페이지 레이아웃 |
| **김도현** | 회원관리, 문의사항 게시판, 회원가입 및 로그인, 커스텀 에러페이지 |
| **조하은** | 회원 일정관리, 트레이너 코멘트관리, 캘린더API연동 |

---

## 🗓️ **프로젝트 기간**
###  **2024-11-27 ~ 2024-12-13**

---
## 🚀 **프로젝트 수행 절차**
 ### <span style="color:#FF5733;">1️⃣</span> **주제선정 및 기획 의도 선정**  
 ### <span style="color:#33A1FF;">2️⃣</span> **요구사항, 기능 정의서 작성**  
 ### <span style="color:#33FF57;">3️⃣</span> **DB 설계, 피그마 화면 설계**  
 ### <span style="color:#FF33F6;">4️⃣</span> **기능 개발 **  
 ### <span style="color:#FFD700;">5️⃣</span> **테스트 및 수정, 결과보고서 작성**


---

<details>
<summary><h2>🛠️ <strong>개발 환경</strong></h2></summary>
  
  
  ![개발 환경 이미지](https://chestnut-blinker-ca6.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8cd794c0-c633-4008-b289-af6deeea8c4d%2Fa15b7295-4884-4d79-a760-47e8a23443c3%2Fimage.png?table=block&id=169902bd-b12f-8054-9a60-fc844af24385&spaceId=8cd794c0-c633-4008-b289-af6deeea8c4d&width=1090&userId=&cache=v2)
</details>


---

<details>
<summary><h2>📑 <strong>요구사항 정의서</strong></h2></summary>
  
   ![image](https://github.com/user-attachments/assets/6628cdef-0e84-469a-9f1a-c7409b145b70)
</details>


---

<details>
<summary><h2>🗒️ <strong>기능 정의서</strong></h2></summary>
  
  - 사용자(유저) 기능 정의서
 
    ![image](https://github.com/user-attachments/assets/36f473e1-be4c-412c-aa74-a4ae4850f6ea)  
  - 관리자 기능 정의서

    ![image](https://github.com/user-attachments/assets/6e0eef6f-38a4-46fc-9309-9233e7c01a9a)
</details>

---

<details>
<summary><h2>🗂️ <strong>ERD</strong></h2></summary>
  
  ![ERD 이미지](https://chestnut-blinker-ca6.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8cd794c0-c633-4008-b289-af6deeea8c4d%2F2317d53d-12bb-40e1-bf7e-43a3f29dda8a%2Fimage.png?table=block&id=16b902bd-b12f-8032-8568-e03391399423&spaceId=8cd794c0-c633-4008-b289-af6deeea8c4d&width=1920&userId=&cache=v2)
</details>


---

<details>
  <summary><h2>📃 <strong>테이블 정의서</strong></h2></summary>

  **users**  
  ![파일 1](https://drive.google.com/uc?export=view&id=1uIkf6OmogJD5af0uHBwp7YV3TTbpH3VZ)

  **user_auth**  
  ![파일 2](https://drive.google.com/uc?export=view&id=1jN24i-GWXzEaQF1ihnPVtD_BxswsBx_r)

  **trainer_profile**  
  ![파일 3](https://drive.google.com/uc?export=view&id=1TPs7lZxuO-pEYjJHMdNwJWV61aEUtdf9)

  **ticket**  
  ![파일 4](https://drive.google.com/uc?export=view&id=1scpM-FcbpngM4dxbmNXPtbgUdVckzusZ)

  **reservation**  
  ![파일 5](https://drive.google.com/uc?export=view&id=1xvzmCFNDani9r9_2bwRL4lEzRCZBXmIN)

  **qr_code**  
  ![파일 6](https://drive.google.com/uc?export=view&id=18iWrwLE9zu-smJXRqkG0e2l2ZFN8uTqI)

  **plan**  
  ![파일 7](https://drive.google.com/uc?export=view&id=1b2RnAr5tEDigonnXHQxR9BaWqA42da09)

  **persistent_logins**  
  ![파일 8](https://drive.google.com/uc?export=view&id=18N74mHKPW_teWLFvMnrmO_g9BT7kMB_X)

  **file**  
  ![파일 9](https://drive.google.com/uc?export=view&id=1LZ9m864zA8lH-9QELMlB3eMvwYrSozVY)

  **comment**  
  ![파일 10](https://drive.google.com/uc?export=view&id=1XMzew_jB9ZQr48ADICPEvtZIUg27Fw4E)

  **buy_list**  
  ![파일 11](https://drive.google.com/uc?export=view&id=1jBHzorTvyaTU4mbavzbr8hU-AmjM0GAV)

  **board**  
  ![파일 12](https://drive.google.com/uc?export=view&id=1EttYQgue7v7_pV5ST8ZMe9LE25uHIALg)

  **attendance**  
  ![파일 13](https://drive.google.com/uc?export=view&id=1XhCdlUg7401eCwPyEmInVpV7fgyPHMJJ)

  **answer**  
  ![파일 14](https://drive.google.com/uc?export=view&id=1ecTX_u14K37SFcFf8VHYahDJGN8yru5g)

</details>                                                                                                                    

---

&nbsp;

# ✨📊 **화면 설계**

<details>
  <summary><h3>🖥️ <strong>메인</strong></h3></summary>
    
  ![메인 화면 1](https://drive.google.com/uc?id=1Uc68G_fKXEXpbpQwmw_ehMtj28TQcP8E)
  ![메인 화면 2](https://drive.google.com/uc?id=1Uo2AOU0S0dM2wE4NOvHmBgi7-4WmaeVt)
  ![메인 화면 3](https://drive.google.com/uc?id=1hSRDulnuxRf6r9kk7ylSZxzFKHRB786z)
  ![메인 화면 4](https://drive.google.com/uc?id=1Up9a7utmk008C51bCwTNk0_pb48Jv1id)
  
</details>

---

<details>
  <summary><h3>👨‍💻 <strong>사용자</strong></h3></summary>
    
  ![사용자 화면 1](https://drive.google.com/uc?id=1Vns8UHr9Czb7mCp0oRkMvdlziCZicLsb)
  ![사용자 화면 2](https://drive.google.com/uc?id=1gKsHrmqugAXwskpHHzOj2QhOeXQhdDNS)
  ![사용자 화면 3](https://drive.google.com/uc?id=1hTpezJdzQLhH3LDQCH-kPDpb6x81u74a)
  ![사용자 화면 4](https://drive.google.com/uc?id=1-AcOC5ooqzOqnshUxfqrcAs0YUFyUmvD)
  ![사용자 화면 5](https://drive.google.com/uc?id=1ja-sOvZ2j7p15DMnEtXviqiN_18NJiFO)
  ![사용자 화면 6](https://drive.google.com/uc?id=1rLu826EyHDDes2J4IM-6gAtZn4Cc6fnO)
  ![사용자 화면 7](https://drive.google.com/uc?id=121WbRNX12d6LvIDWxccaLHhtfDm-Uls_)
  ![사용자 화면 8](https://drive.google.com/uc?id=15MMalJR4fd_4Sw1fhrwTd9Slbw16W2Nb)
  ![사용자 화면 9](https://drive.google.com/uc?id=1uQtkwajZWYumxgvRC3jHBNLXscEqlHeo)
  ![사용자 화면 10](https://drive.google.com/uc?id=1ZvEBl78uv1MwJyduIHh-0K1Vk6SXxbLd)
  ![사용자 화면 11](https://drive.google.com/uc?id=1mtO7EgM51fYJFiVh_QgKi_wR7rVK3xrM)
  ![사용자 화면 12](https://drive.google.com/uc?id=1gI3UU8nHksHzgKc-NwUHKV531sbQYdz4)

</details>

---

<details>
  <summary><h3>🛒 <strong>구매</strong></h3></summary>
    
  ![구매 화면 1](https://drive.google.com/uc?export=view&id=1mfs0TBP-93EDxgPWtjHDm4CnJVeJubfB)
  ![구매 화면 2](https://drive.google.com/uc?export=view&id=18RUIJA9hgrSM4asnp4GHeVSeJUM5nU2U)
  ![구매 화면 3](https://drive.google.com/uc?export=view&id=1O1zKaSeBCMyvTAML6RTrIiW_SLpC9a0Z) 
  ![구매 화면 4](https://drive.google.com/uc?export=view&id=1Vg5ufkETUEam0LcLRuNLrD_yTwCPGmRk)
  ![구매 화면 5](https://drive.google.com/uc?export=view&id=1e2nNdmhYeJwS6UnuKpsi71-YPCO8GnGT)
</details>


---

<details>
 <summary><h3>📝 <strong>게시판</strong></h3></summary>
    
  ![게시판 화면 1](https://drive.google.com/uc?id=1lhLfroTuvEUKkrS40B0SRMAAIXFGQDxh)
  ![게시판 화면 2](https://drive.google.com/uc?id=1w7vIgGkxcYcaTNVFk99gEY_SWYGc5Cb_)
</details>

---

<details>
  <summary><h3>🗓️ <strong>운동계획표</strong></h3></summary>
    
  ![운동계획표 화면](https://drive.google.com/uc?id=1sO1FP1Lua8ykdPyNZTe1AYWVymS02IGD)
</details>

---

<details>
  - <summary><h3>🛠️ <strong>관리자</strong></h3></summary>
    
  ![관리자 화면 1](https://drive.google.com/uc?id=1B8LTUYdehlfvMRUd_AXhCsrNtRSCAxP0)
  ![관리자 화면 2](https://drive.google.com/uc?id=11yqmvEDOs5DemuaqLkJ0RhJ-JkmdvOAx)
  ![관리자 화면 3](https://drive.google.com/uc?id=1gcoGjZpWV_wkz-ICyLhzw0Y9tHmcPOeP)
  ![관리자 화면 4](https://drive.google.com/uc?id=1wtZrhqDV9H6h6NplAFcTzQ8YPuzwYTKl)
  ![관리자 화면 5](https://drive.google.com/uc?id=1773uAE71llv-bJMFk-D1brLXNzU6U_ez)
  ![관리자 화면 6](https://drive.google.com/uc?id=1pjCkvkHeufRn-x5q0Bw39DPuT_83-0yX)
  ![관리자 화면 7](https://drive.google.com/uc?id=1gRzloprNVjySX3Ec0ZQm-fFzWgoKt9N8)
  ![관리자 화면 8](https://drive.google.com/uc?id=14XyRjNqg1T7f7kY1ZfO8JKdwCuqIGOdj)
  ![관리자 화면 9](https://drive.google.com/uc?id=1Dfd6QNfg9ZojGtGAkNB9r0D4V3bkWBTN)
  ![관리자 화면 10](https://drive.google.com/uc?id=1JhYmLXcIyMLIJhVfyU7UqtgbpuYWDFan)
  ![관리자 화면 11](https://drive.google.com/uc?id=1IKwCA9CU7cHNaaULMDOWbQ2KHbLP3AiX)
</details>

---


&nbsp;

<summary><h3>📝 <strong>느낀점</strong></h3></summary>
    
**이세진**
- **한계점**: 팀원들의 의견을 취합하고, 타협하고 설득해가며 프로젝트를 진행하였는데, 서로 너무 배려하다보니 시간적인 부분에서 한계점을 느꼈습니다.
- **보완점**: 프로젝트를 진행하며 팀 간의 분위기를 풀어내어 더욱 소통을 활성화 시키는 방향으로 진행하였습니다. 모르는 부분이 있으면 팀원에게 물어보고, 팀원이 헤매는 것 같으면 먼저 다가가 문제를 같이 해결하기도 하였습니다. 그러하여 적극적으로 소통하게 되다보니, 프로젝트의 결정점에 대하여 시간이 점점 단축되었습니다.
- **소감**: 결제 API 를 처음 다루어보았는데, 직접 개발한 유저 데이터나 ticket(상품)에 대한 정보를 API 연동 시 담아 실행했던 것이 신기하여 기억에 남습니다. 포트원 API를 사용하였는데 처음 접하는 코드들이다 보니 어떤 코드를 담아야 하는지 생각하면서 직접 등록한 정보를 담는 방식이 재미있게 느껴졌습니다. 또한, 설계부터 시작하여 역할 분담, 개발, 테스트, 발표까지 프로젝트를 진행하면서 실력 향상과 실질적인 능력 향상을 많이 얻을 수 있어 뿌듯하고 보람찬 경험이었습니다.




---


  





