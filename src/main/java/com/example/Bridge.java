package com.example;

public class Bridge
{
    public static void main(String[] args) 
    {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel push = new PushChannel();

        System.out.println("--- Part 1: Base Scenarios ---");
        
        Payment onlineEmail = new OnlinePaymentNotification(email);
        System.out.println(onlineEmail.processPayment());

        Payment onlineSms = new OnlinePaymentNotification(sms);
        System.out.println(onlineSms.processPayment());

        Payment codEmail = new CashOnDeliveryPayment(email);
        System.out.println(codEmail.processPayment());

        Payment codSms = new CashOnDeliveryPayment(sms);
        System.out.println(codSms.processPayment());

        System.out.println("\n--- Part 2: Bitcoin Payment Scenarios ---");
        
        Payment bitcoinEmail = new BitcoinPayment(email);
        System.out.println(bitcoinEmail.processPayment());

        Payment bitcoinSms = new BitcoinPayment(sms);
        System.out.println(bitcoinSms.processPayment());

        System.out.println("\n--- Part 3: Push Notification Scenarios ---");
        
        Payment onlinePush = new OnlinePaymentNotification(push);
        System.out.println(onlinePush.processPayment());

        Payment bitcoinPush = new BitcoinPayment(push);
        System.out.println(bitcoinPush.processPayment());
    }

    public interface NotificationChannel 
    {
        String sendNotification(String message);
    }

    public static class EmailChannel implements NotificationChannel 
    {
        @Override
        public String sendNotification(String message) 
        {
            return "[Email Notification] " + message;
        }
    }

    public static class SmsChannel implements NotificationChannel 
    {
        @Override
        public String sendNotification(String message) 
        {
            return "[SMS Notification] " + message;
        }
    }

    public static class PushChannel implements NotificationChannel 
    {
        @Override
        public String sendNotification(String message) 
        {
            return "[Push Notification] " + message;
        }
    }

    public abstract static class Payment 
    {
        protected NotificationChannel notificationChannel;

        public Payment(NotificationChannel channel) 
        {
            this.notificationChannel = channel;
        }

        public abstract String processPayment();
    }

    public static class OnlinePaymentNotification extends Payment 
    {
        public OnlinePaymentNotification(NotificationChannel channel) 
        {
            super(channel);
        }

        @Override
        public String processPayment() 
        {
            String paymentMessage = "Processing online payment.";
            String notificationMessage = notificationChannel.sendNotification(paymentMessage);
            return notificationMessage;
        }
    }

    public static class CashOnDeliveryPayment extends Payment 
    {
        public CashOnDeliveryPayment(NotificationChannel channel) 
        {
            super(channel);
        }

        @Override
        public String processPayment() 
        {
            String paymentMessage = "Processing cash on delivery payment.";
            String notificationMessage = notificationChannel.sendNotification(paymentMessage);
            return notificationMessage;
        }
    }

    public static class BitcoinPayment extends Payment 
    {
        public BitcoinPayment(NotificationChannel channel) 
        {
            super(channel);
        }

        @Override
        public String processPayment() 
        {
            String paymentMessage = "Processing Bitcoin payment.";
            String notificationMessage = notificationChannel.sendNotification(paymentMessage);
            return notificationMessage;
        }
    }
}