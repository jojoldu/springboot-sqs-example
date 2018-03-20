# Spring Boot Cloud + AWS SQS

Spring Boot Cloud 와 AWS SQS 연습용 프로젝트  

## 0. Usage

access-example-application.yml 파일명을 access-application.yml로 변경후 인증값 할당

## 1. Basic

* Messages in Flight
  * 전달 대기 중이 아니며 이미 전달되었지만 더 이상 소비자가 처리하지 않은 메시지.
  * [why-do-sqs-messages-sometimes-remain-in-flight-on-queue](https://stackoverflow.com/questions/19792881/why-do-sqs-messages-sometimes-remain-in-flight-on-queue)

## 2. Dead Letter Queue

Dead Letter Queue(줄여서 DLQ)는 **배달 못한 메세지 큐**로 불립니다.  
    
처리하지 못한 SQS 메세지는 다른 작업자가 큐를 들여다볼때 다시 나타납니다.  
이를 **Retry** (재시도) 라고 합니다.  
하지만 어떤 이유(코드 버그, SQS 문제 등등)로 인해서 **모든 재시도가 실패**하면 메세지는 큐에 영원히 남게 되고
계속해서 재시도를 하게 됩니다.  
이렇게 되면 **절대 처리되지 않는 메세지**가 되는 것이며, 자원의 낭비도 심합니다.  
이를 방지하기 위해 DLQ (배달 못한 메세지큐)를 구성하게 됩니다.  
  
DLQ의 방식은 간단합니다.  
**메세지를 특정 횟수 이상 재시도하면 원래 큐에서 제거되고 DLQ로 전달**이 끝입니다.  

DLQ는 기존 큐와 한가지 차이가 있습니다.  

* 작업자들은 DLQ의      


## Local Development