#include <16f877a.h>
#device adc=10
#include <stdio.h>

#fuses XT, NOWDT, NOPROTECT
#define LED PORTB, 0
#byte PORTB = 6

#use delay (clock=4MHz)
#use rs232 (baud=9600, parity=N, xmit=pin_c6, rcv=pin_c7, bits=8)

void main() {
   int16 dato1;
   float r1;
   
   setup_adc_ports(all_analog);
   setup_adc(adc_clock_internal);
   Set_Tris_B(0b11111110);
   
   while (true) {
      set_adc_channel(0);
      delay_ms(500);
      dato1 = read_adc();
      r1 = 0.004887f * dato1;
      printf("Lectura en LSB canal 0 %4Ld\r\n", dato1);
      printf("Valor de voltaje %f Volts\r\n", r1);
      
      if (r1 > 0.50) {
         bit_set(LED);
      } else {
         Bit_clear(LED);
      }
   }
}
