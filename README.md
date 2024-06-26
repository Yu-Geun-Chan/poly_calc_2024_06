# 목표
- 3 * 1 + (1 - (4 * 1 - (1 - 1)))


        int stripOuterBrakets = 0;

        while (exp.charAt(stripOuterBrakets) == ('(') && exp.charAt(exp.length() - 1 - stripOuterBrakets) == (')')) {
            stripOuterBrakets++;
        }
        if (stripOuterBrakets == 0) {
            return exp;
        } else {
          exp = exp.substring(1, exp.length() - 1);
            return exp;