# Shebang

Shebang is an information security challenge in the Miscellaneous category, and was presented to participants of [KAF CTF 2019](https://ctf.kipodafterfree.com)

## Challenge story

No Story

## Challenge exploit

Just a game you have to beat using a fast script

## Challenge solution

No need

## Building and installing

[Clone](https://github.com/NadavTasher/2019-Shebang/archive/master.zip) the repository, then type the following command to build the container:
```bash
docker build . -t shebang
```

To run the challenge, execute the following command:
```bash
docker run --rm -d -p 1120:8000 shebang
```

## Usage

You may access the challenge through `nc localhost 1120`

## Flag

Flag is:
```flagscript
KAF{wr1t3_a_5cript_t0_w1nn_th1s_0n3}
```

## License
[MIT License](https://choosealicense.com/licenses/mit/)