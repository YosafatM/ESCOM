#include <16f877a.h>
#include <stdio.h>
#fuses XT, NOWDT, NOPROTECT
#use delay (clock=4000000)
#use rs232 (baud=9600, parity=N, xmit=pin_c6, rcv=pin_c7,  bits=8)
#use fast_io(D)

#byte PORTD = 8
#byte PORTA = 5

#define LED PORTD, 0
#define BTN PORTA,0
char dato;

#INT_RDA
void rda_isr(){
   dato=getc();

   switch(dato){
      case '1':     
               bit_clear(LED);
               break;
      case '0':       
               bit_set(LED);
               break;
   }   
}

void main(){
   set_tris_d(0xF0);
   PORTD=0x00;
   set_tris_a(0x0F);
   
   enable_interrupts(int_rda);
   enable_interrupts(global);
   
   while(true){
      delay_ms(50);
      
      if(bit_test(BTN)){
         putchar('0');
      } else {
         putchar('1');               
      }
   }
}


