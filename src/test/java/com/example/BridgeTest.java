package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.example.Bridge.BitcoinPayment;
import com.example.Bridge.CashOnDeliveryPayment;
import com.example.Bridge.EmailChannel;
import com.example.Bridge.NotificationChannel;
import com.example.Bridge.OnlinePaymentNotification;
import com.example.Bridge.Payment;
import com.example.Bridge.PushChannel;
import com.example.Bridge.SmsChannel;

public class BridgeTest 
{
    @Test
    public void testEmailChannelReturnedString() 
    {
        NotificationChannel channel = new EmailChannel();
        String result = channel.sendNotification("Test Message");
        
        String expected = "[Email Notification] Test Message";
        assertEquals(expected, result, "The email channel output string did not match.");
    }

    @Test
    public void testSmsChannelReturnedString() 
    {
        NotificationChannel channel = new SmsChannel();
        String result = channel.sendNotification("Test Message");
        
        String expected = "[SMS Notification] Test Message";
        assertEquals(expected, result, "The SMS channel output string did not match.");
    }

    @Test
    public void testPushChannelReturnedString() 
    {
        NotificationChannel channel = new PushChannel();
        String result = channel.sendNotification("Test Message");
        
        String expected = "[Push Notification] Test Message";
        assertEquals(expected, result, "The Push channel output string did not match.");
    }

    @Test
    public void testOnlinePaymentWithEmailReturnedString() 
    {
        Payment payment = new OnlinePaymentNotification(new EmailChannel());
        String result = payment.processPayment();
        
        String expected = "[Email Notification] Processing online payment.";
        assertEquals(expected, result, "The online payment email message did not match.");
    }

    @Test
    public void testCashOnDeliveryWithSmsReturnedString() 
    {
        Payment payment = new CashOnDeliveryPayment(new SmsChannel());
        String result = payment.processPayment();
        
        String expected = "[SMS Notification] Processing cash on delivery payment.";
        assertEquals(expected, result, "The cash on delivery SMS message did not match.");
    }

    @Test
    public void testBitcoinPaymentWithPushReturnedString() 
    {
        Payment payment = new BitcoinPayment(new PushChannel());
        String result = payment.processPayment();
        
        String expected = "[Push Notification] Processing Bitcoin payment.";
        assertEquals(expected, result, "The Bitcoin payment push message did not match.");
    }
}
