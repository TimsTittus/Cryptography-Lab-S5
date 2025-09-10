#include <stdio.h>
#include <string.h>
#include <ctype.h>

int mod26(int x) { x %= 26; if (x < 0) x += 26; return x; }

void hill_encrypt(int v[2], int key[2][2], int out[2]) {
    out[0] = mod26(key[0][0]*v[0] + key[0][1]*v[1]);
    out[1] = mod26(key[1][0]*v[0] + key[1][1]*v[1]);
}

void hill_decrypt(int v[2], int inv[2][2], int out[2]) {
    out[0] = mod26(inv[0][0]*v[0] + inv[0][1]*v[1]);
    out[1] = mod26(inv[1][0]*v[0] + inv[1][1]*v[1]);
}

int main(void) {
    int key[2][2] = {{2, 3}, {1, 4}};
    int inv_key[2][2] = {{6, 15}, {5, 16}};

    char plaintext[200];
    printf("Enter plaintext: ");
    if (scanf("%199s", plaintext) != 1) return 0;

    for (int i = 0; plaintext[i]; ++i) plaintext[i] = toupper((unsigned char)plaintext[i]);

    int len = strlen(plaintext);
    if (len % 2 == 1) {
        plaintext[len] = 'X';
        plaintext[len+1] = '\0';
        len++;
    }

    printf("Plaintext:  %s\n", plaintext);

    int cipher_nums[200];
    int v[2], out[2];

    printf("Ciphertext: ");
    for (int i = 0; i < len; i += 2) {
        v[0] = plaintext[i]   - 'A';
        v[1] = plaintext[i+1] - 'A';
        hill_encrypt(v, key, out);
        cipher_nums[i]   = out[0];
        cipher_nums[i+1] = out[1];
        printf("%c%c", out[0] + 'A', out[1] + 'A');
    }

    printf("\nDecrypted:  ");
    for (int i = 0; i < len; i += 2) {
        v[0] = cipher_nums[i];
        v[1] = cipher_nums[i+1];
        hill_decrypt(v, inv_key, out);
        printf("%c%c", out[0] + 'A', out[1] + 'A');
    }
    printf("\n");
    return 0;
}