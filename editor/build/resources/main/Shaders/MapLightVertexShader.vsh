
attribute vec4 a_color;
attribute vec3 a_position;
attribute vec2 a_texCoord0;


uniform  mat4 u_projTrans;
uniform float  worldUnitMutiplier;
uniform float bright;
uniform int numberOfLights;
uniform vec2  lightVectors[numberOfLights];
uniform float  lightBrightness[numberOfLights];



varying  vec4 v_color;
varying vec2 v_texCoord0;
varying float f_brightness;
varying  int f_numberOfLights;
varying vec2 f_lightvectors[numberOfLights];
varying float  f_lightBrightness[numberOfLights];
varying float  f_worldUnitMutiplier;



void main(){
v_color=a_color;
    v_texCoord0=a_texCoord0;
    gl_Position=u_projTrans*vec4(a_position, 1.0);
    f_brightness=bright;
    f_numberOfLights=numberOfLights;
    f_lightVectors=lightVectors;
    f_lightBrightness=lightBrightness;
    f_worldUnitMutiplier=worldUnitMutiplier;






}