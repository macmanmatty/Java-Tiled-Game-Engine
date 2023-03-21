package com.jessematty.black.tower.Generators.Sets;

/**
// enum class for the masking mode bit numbers
// full wang has all bit numbers
// partial wang is missing single numbers and corners 2, 8, 64, 16 , 10, 24, 72, 90, 66, 74, 82, 26, 56
// no diagonals is missing the diagonals 126, and 213
 */

public enum MaskMode {
    FULL_WANG_SET,  PARTIAL_WANG_SET, PARTIAL_WANG_SET_NO_DIAGONALS, CUSTOM;

}
