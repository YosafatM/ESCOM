#include <16f877a.h>
#fuses hs, nowdt, noprotect, noput
#use delay (clock=4000000)
#include <stdio.h>
#use rs232 (baud=9600, parity=N, xmit=pin_c6, rcv=pin_c7, bits=8)

int8 dato, dato1;

#int_timer0
void timer_() {
   set_timer0(0);
}

void main() {
   setup_timer_0(rtcc_ext_h_to_l|rtcc_div_1);
   set_timer0(0);
   enable_interrupts(int_timer0);
   enable_interrupts (global);
   dato=get_timer0();
   dato1=dato;
   while (true) {
      dato = get_timer0();
      
      if(dato != dato1) {
         printf("%3u\r\n",dato);
         dato1=dato;
      }
   }
}
