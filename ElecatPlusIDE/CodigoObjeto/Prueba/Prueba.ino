(1,int,variable1, , )
(2,=,variable1,0, )
setup{
(5,int,ledIzquierda, , )
(6,=,ledIzquierda,3, )
(7,int,ledDerecha, , )
(8,=,ledDerecha,9, )
(9,int,claxon, , )
(10,=,claxon,12, )
(11,int,sensor, , )
(12,=,sensor,10, )
(13,int,botonera, , )
(14,=,botonera,3, )
(15,int,servomotor, , )
(16,=,servomotor,11, )
}
(19,loop,ejecutar,{, )
(20,int,tono, , )
(23,=,tono,6319, )
(24,int,duracion, , )
(25,=,duracion,3000, )
(26,girar,servomotor,0, )
(27,delay,1000, , )
(28,girar,servomotor,30, )
(29,delay,1000, , )
(30,girar,servomotor,60, )
(31,delay,1000, , )
(32,girar,servomotor,90, )
(33,delay,1000, , )
(34,sonar,claxon,duracion,tono)
(35,detectar,botonera,tono, )
(36,},ejecutar, , )
