int sensor=10;
int proto2=9;
int proto1=8;
int botonera=3;
int ENB = 3;
int IN3 = 2;
int IN4 = 4;
int ENA = 5;
int IN1 = 7;
int IN2 = 6;
int claxon=12;
int ledDerecha=9;
int ledIzquierda=8;
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C display(0x27, 16, 2);
#include <Servo.h>
Servo piez;
int variable1=0;
void setup(){
piez.attach(11);
display.init();
display.backlight();
pinMode(ledIzquierda,OUTPUT);
pinMode(ledDerecha,OUTPUT);
pinMode(claxon,OUTPUT);
pinMode(ENA,OUTPUT);
pinMode(IN1,OUTPUT);
pinMode(IN2,OUTPUT);

pinMode(ENB,OUTPUT);
pinMode(IN3,OUTPUT);
pinMode(IN4,OUTPUT);

pinMode(botonera,INPUT);
pinMode(proto1,OUTPUT);
pinMode(proto2,OUTPUT);
pinMode(sensor,INPUT);
}

void loop(){
digitalWrite(proto1,HIGH);
delay(1000);
digitalWrite(proto1,LOW);
delay(1000);
digitalWrite(proto2,HIGH);
delay(1000);
digitalWrite(proto2,LOW);
delay(1000);
digitalWrite(proto1,HIGH);
delay(1000);
digitalWrite(proto1,HIGH);
delay(1000);
}
