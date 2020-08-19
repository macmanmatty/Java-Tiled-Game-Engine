varying vec4 v_color;
varying vec2 v_texCoord0;
varying  float f_brightness;



uniform sampler2D u_sampler2D;



void main (){
    vec4 color=texture2D(u_sampler2D, v_texCoord0)*v_color;
    color.rgb=color.rgb*2.0*f_brightness;



    gl_FragColor= color;






}