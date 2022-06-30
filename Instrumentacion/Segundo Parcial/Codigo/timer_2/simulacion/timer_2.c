#include <16f877a.h>
#fuses xt, nowdt, noprotect, nolvp, put, brownout
#use delay (clock=4000000)

int16 duty;
int Timer2, Poscaler;

void main() {
   Timer2 = 249;
   Poscaler = 1;
   set_tris_c(0b11111110);
   setup_timer_2(t2_div_by_4, Timer2, Poscaler);
   setup_ccp1(ccp_pwm);
   
   duty = 0;
   
   while (true) {
      set_pwm1_duty(duty);
      duty = duty + 50;
      delay_ms(200);
      
      if (duty >= 1023) {
         duty = 0;
      }
   }
}
