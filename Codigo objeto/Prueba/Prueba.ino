int claxon=12;
int ledDerecha=9;
int ledIzquierda=18;
int variable1=0;
void setup(){
pinMode(ledIzquierda,OUTPUT);
pinMode(ledDerecha,OUTPUT);
pinMode(claxon,OUTPUT);
}

void loop(){
if(variable1<=3){
digitalWrite(ledIzquierda,HIGH);

digitalWrite(ledIzquierda,LOW);
delay(500);
variable1=variable1+1;
}

if(variable1>3){
digitalWrite(ledDerecha,HIGH);
delay(500);
digitalWrite(ledDerecha,LOW);

}

}

void pitarVeces(){

}
