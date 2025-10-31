# 🏦 Merchant Onboarding & KYC Verification System (Razorpay-Inspired)

> ⚠️ **Disclaimer:**  
> This project is **not affiliated with Razorpay**. It was created **solely for learning and demonstration purposes** — to simulate how fintech platforms like Razorpay handle merchant onboarding, KYC verification, and anomaly detection in real-time.

---

## 🚀 Overview

The **Merchant Onboarding & KYC Verification System** is a full-stack project designed to replicate a simplified **fintech onboarding process** — inspired by **Razorpay’s real-world workflows**.

It enables:
- Secure **merchant registration**  
- Automated **KYC verification**
- **Anomaly detection** on transaction data to identify potential fraud
- Deployment using **Docker** and **Render**

This project showcases how backend systems can combine **Spring Boot**, **MongoDB**, and **anomaly detection algorithms** to create reliable and scalable fintech solutions.

---

## 🧩 Key Features

✅ **Merchant Onboarding** — Register merchants with details like business info, GST number, and contact details  
✅ **KYC Verification** — Simulated validation using merchant ID (e.g., `M-1234`)  
✅ **Anomaly Detection Service** — Detects suspicious transactions (e.g., large amounts, missing merchant data)  
✅ **Dashboard** — Visualize registered merchants and verification status  
✅ **Dockerized Deployment** — Easily deployable via Docker and Render  

---

## 🏗️ Tech Stack

**Frontend:** React, Axios, React Router  
**Backend:** Spring Boot (Java 17), REST API  
**Database:** MongoDB  
**Deployment:** Docker + Render  

---

## 🧠 Problem Solved

Fintech companies like Razorpay onboard thousands of merchants daily.  
However, manual verification and inconsistent data can cause:
- Delays in activation  
- Fraudulent accounts  
- Poor customer experience  

💡 **Solution:**  
This project automates KYC validation and introduces basic **anomaly detection logic** to flag risky merchants early — improving speed, trust, and data reliability.

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/AMAN25800/AnomalyDetector.git
cd AnomalyDetector
