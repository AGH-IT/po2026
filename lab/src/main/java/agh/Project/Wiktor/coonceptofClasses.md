# class rollstats that:
    1. on start rolls 5 d20 sorts them and asks if player wants to reroll any
        a. if yes rerolls the dices and rolls 1d8 if:
            d8 lands on 1 all rerolled d20s become min value(1)
            d8 lands on 8 all rerolled d20s become max value(20)
# Class race that:
    pulls a table from Json file and allows player to chose one for their character;
    adds racial bonuses to character :
        str mod ; agi mod ; wis mod; char mod ; TL - most imortant one and returns them
# class stats that:
    1. import rolled values from rollstats and race  and allows user to assgin them to four stats:
        strength
        agility
        wisdom
        charisma
        TL (from race)
    2. calculates four derived statistics:
        a. health = (strength*agility*TL)/20
        b. mana = (wisdom*agility*TL)/20
        c. shield= rounded up (health)/3
        d. barrier = rounded up(mana)/3