#INCLUDE <16f877a.h>
#USE DELAY (CLOCK = 4M)
#FUSES XT, NOWDT

int8 i;
void main(){
   Set_Tris_B(0x00);
   
   while (true){
      for (i = 0; i < 9; i++) {
         output_b(0x01<<i);
         delay_ms(50);
      }
      for (i = 0; i < 9; i++) {
         output_b(0x80>>i);
         delay_ms(50);
      }
   }
}
