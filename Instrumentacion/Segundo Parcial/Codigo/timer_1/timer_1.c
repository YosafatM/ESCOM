#include <16f877a.h>
#fuses hs, nowdt, noprotect, noput
#use delay (clock=4000000)
#include <stdio.h>
#use rs232 (baud=9600, parity=N, xmit=pin_c6, rcv=pin_c7, bits=8)

int16 dato, dato1;

#int_timer1
void timer_1() {
   set_timer1(0);
}

void main() {
   setup_timer_1(T1_EXTERNAL|T1_DIV_BY_1);
   set_timer1(0);
   enable_interrupts(int_timer1);
   enable_interrupts(global);
   dato=get_timer1();
   dato1=dato;
   while (true) {
      dato = get_timer1();
      
      if(dato != dato1 && (dato%500 == 0 || dato == 65535)) {
         printf("%Lu\r\n",dato);
         dato1=dato;
      }
   }
}
