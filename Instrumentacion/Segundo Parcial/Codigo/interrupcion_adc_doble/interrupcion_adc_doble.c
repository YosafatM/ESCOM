#include <16f877a.h>
#device adc=10
#include <stdio.h> 
#fuses XT, NOWDT, NOPROTECT
#use delay (clock=4MHz)
#use rs232 (baud=9600, parity=N, xmit=pin_c6, rcv=pin_c7, bits=8)

void leersensor1 ();
void leersensor2 ();
char dato;
float valor;
int16 lectura;

#int_rda
void rds_isr() {
   dato = getc();
   
   switch(dato) {
      case '1':
         leersensor1();
      break;
      
      case '2':
         leersensor2();
      break;
   }
}

void main() {
   printf("Introduzca una opci?n 1 o 2\r\n");
   enable_interrupts(int_rda);
   enable_interrupts(global);
   while(true) { }
}

void leersensor1() {
   setup_adc_ports(all_analog);
   setup_adc (adc_clock_internal);
   set_adc_channel(0);
   delay_us(500);
   lectura = read_adc();
   valor = 0.004887f * lectura;
   printf("El dato del sensor 1: %f \r\n", valor);
}

void leersensor2() {
   setup_adc_ports(all_analog);
   setup_adc(adc_clock_internal);
   set_adc_channel(1);
   delay_us(500);
   lectura = read_adc();
   valor = 0.004887f * lectura;
   printf("El dato del sensor 2 es: %f \r\n", valor);
}
