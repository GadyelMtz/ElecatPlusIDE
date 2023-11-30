void setup{
(2,display_lcd,display_silla, , )
(3,led,ledIzquierda, , )
(4,=,ledIzquierda,8, )
(5,led,ledDerecha, , )
(6,=,ledDerecha,9, )
(7,buzzer,claxon, , )
(8,=,claxon,12, )
(9,sensor_distancia,sensor, , )
(10,=,sensor,10, )
(11,boton,botonera, , )
(12,=,botonera,3, )
(13,servo,servomotor, , )
(14,=,servomotor,11, )
}
void loop(){
int variable1=0;

sensor=digitalRead(variable1);
servomotor.write(30);
display_silla.clear(); 
display_silla.print("Hola que tal");
(22,<=,variable1,3,t1)
(23,while,t1,{, )
digitalWrite(ledIzquierda,HIGH);
delay(250);
digitalWrite(ledIzquierda,LOW);
delay(250);
(28,+,variable1,1,t1)
(29,=,variable1,t1, )
}
(31,>,variable1,3,t1)
(32,<,variable1,6,t2)
(33,&&,t1,t2,t3)
(34,while,t3,{, )
digitalWrite(ledDerecha,HIGH);
delay(500);
digitalWrite(ledDerecha,LOW);
delay(500);
(39,+,variable1,1,t1)
(40,=,variable1,t1, )
}
int tono=500;

int duracion=1000;

tone(claxon,duracion,tono));
}
void pitarVeces(int veces){
(49,int,veces, , )
(50,),{, , )
(51,int,veces, , )
int i=0;

(54,<,i,3,t1)
(55,+,i,1,t1)
(56,=,i,t1, )
(57,for,t1,{, )
tone(claxon,100,1000));
delay(250);
}
(61,>,veces,3,t1)
(62,if,t1,{, )
delay(400);
}
}
