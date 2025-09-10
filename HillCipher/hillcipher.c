#include <stdio.h>
#include <string.h>

void hill_cipher(int msg_vector[2], int key_matrix[2][2]) {
    int encrypted_vector[2];
    encrypted_vector[0] = (key_matrix[0][0] * msg_vector[0] + key_matrix[0][1] * msg_vector[1]) % 26;
    encrypted_vector[1] = (key_matrix[1][0] * msg_vector[0] + key_matrix[1][1] * msg_vector[1]) % 26;

    printf("%c%c", encrypted_vector[0] + 'A', encrypted_vector[1] + 'A');
}

int main() {
    int key_matrix[2][2] = {{2, 3}, {1, 4}};
    char plaintext[] = "CODE";

    printf("Plaintext: %s\n", plaintext);
    printf("Ciphertext: ");

    int len = strlen(plaintext);
    int msg_vector[2];

    for (int i = 0; i < len; i += 2) {
        msg_vector[0] = plaintext[i] - 'A';
        msg_vector[1] = plaintext[i + 1] - 'A';
        hill_cipher(msg_vector, key_matrix);
    }

    printf("\n");
    return 0;
}