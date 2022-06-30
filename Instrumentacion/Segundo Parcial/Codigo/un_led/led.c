#INCLUDE <16f877a.h>
#USE DELAY (CLOCK = 4000000)
#FUSES XT, NOPROTECT, NOWDT
#DEFINE SW PORTA, 1
#DEFINE LED PORTB, 0
#BYTE PORTA = 5
#BYTE PORTB = 6

void main(){
   Set_Tris_A(0b00000010);
   Set_Tris_B(0b11111110);
   
   while (true){
      if (bit_test(SW)) {
         bit_set(LED);
      } else {
         Bit_clear(LED);
      }
   }
}
