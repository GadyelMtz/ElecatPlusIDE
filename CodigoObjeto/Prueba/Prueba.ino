int ENB = 3;
int IN3 = 2;
int IN4 = 4;
int ENA = 5;
int IN1 = 7;
int IN2 = 6;
#include <Servo.h>
Servo servomotor;
int botonera=3;
int sensor=10;
int claxon=12;
int ledDerecha=9;
int ledIzquierda=8;
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C display_silla(0x27, 16, 2);
void setup(){
display_silla.init();
display_silla.backlight();
pinMode(ledIzquierda,OUTPUT);
pinMode(ledDerecha,OUTPUT);
pinMode(claxon,OUTPUT);
pinMode(sensor,INPUT);
pinMode(botonera,INPUT);
servomotor.attach(11);
pinMode(ENA,OUTPUT);
pinMode(IN1,OUTPUT);
pinMode(IN2,OUTPUT);

pinMode(ENB,OUTPUT);
pinMode(IN3,OUTPUT);
pinMode(IN4,OUTPUT);

}

void loop(){
int variable1 = 0;
variable1=digitalRead(sensor);
servomotor.write(30);
display_silla.clear(); 
display_silla.print("Hola que tal");
while(variable1<=3){
digitalWrite(ledIzquierda,HIGH);
delay(250);
digitalWrite(ledIzquierda,LOW);
delay(250);
variable1 = variable1 + 1;
}
while(variable1>3&&variable1<6){
digitalWrite(ledDerecha,HIGH);
delay(500);
digitalWrite(ledDerecha,LOW);
delay(500);
variable1 = variable1 + 1;
}
int tono = 500;
int duracion = 1000;
tone(claxon,500,1000);
pitarVeces(3);
}


void pitarVeces(int veces){
for(int i=0;i<3;i=i+1){
tone(claxon,1000,100);
digitalWrite(IN3,HIGH);
digitalWrite(IN4,LOW);
analogWrite(ENB,400);
delay(250);
}

if(veces > 3){
delay(400);
}
}
