#version 330 core
in vec2 texCoord;
out vec4 fragColor;
uniform sampler2D textureSampler;
uniform sampler2D paletteSampler;

void main()
{
    // Get the original color from the texture
    vec4 originalColor = texture(textureSampler, texCoord);

    // Sample the color from the palette
    vec4 newColor = texture(paletteSampler, vec2(originalColor.r, 0.5));

    // Output the new color
    fragColor = newColor;
}
