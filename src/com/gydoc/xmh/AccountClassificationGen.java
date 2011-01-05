package com.gydoc.xmh;

import com.gydoc.xmh.domain.AccountClassification;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 *
 */
public class AccountClassificationGen {

    private static int index = 0;
    private static String unicodeFormat = "\\u%1$4X";
    private static Map<Integer, Integer> levelAndCount = new HashMap<Integer, Integer>();
    private static Stack<AccountClassification> stacks = new Stack<AccountClassification>();
    
    private static String formatChar(char c) {
        return String.format(unicodeFormat, (int) c);
    }
    
    public static void main(String[] args) throws Exception {
        String s =  "-root`top\n";
               s += "--c01`c01\n";
               s += "---c0101`c0101\n";
               s += "---c0102`c0102\n";
               s += "----c010201`c010201\n";
               s += "--c02`c02\n";
               s += "---c0201`c0201\n";
               s += "--c03`c03\n";
               s += "---c0301`c0301\n";
        BufferedReader br = new BufferedReader(new StringReader(s));
        String line;
        int lastLevel = -1;
        while ((line = br.readLine()) != null) {
            int level = line.lastIndexOf("-");
            line = line.substring(level+1);
            String[] elements = line.split("`");
            
            AccountClassification ac = new AccountClassification();
            ac.setCode(elements[0]);
            ac.setName(elements[1]);
            if (level < lastLevel) {
                handlePC(lastLevel, level);
            }
            lastLevel = level;
            stacks.push(ac);
            int c = levelAndCount.get(level) == null ? 0 : levelAndCount.get(level);
            levelAndCount.put(level, c + 1);
        }
        
        if (lastLevel != 1) {
            handlePC(lastLevel, 1);
        }
        handlePC(1, 0);
        
        ACGen gen = prepareAC(stacks.pop());
        printAC(gen);
    }

    private static void handlePC(int lastLevel, int curLevel) {
        for (int m = lastLevel; m > curLevel; m--) {
            int childrenCount = levelAndCount.get(m);
            List<AccountClassification> l = new LinkedList<AccountClassification>();
            for (int i = 0; i < childrenCount; i++) {
                l.add(stacks.pop());
            }
            AccountClassification p = stacks.peek();
            for (AccountClassification t : l) {
                p.addChild(t);
            }
            levelAndCount.remove(m);
        }
    }

    private static ACGen prepareAC(AccountClassification root) {
        ACGen acg = new ACGen(root);
        acg.setIndex(index++);
        for (AccountClassification ac : root.getChildren()) {
            acg.addChild(prepareAC(ac));
        }
        return acg;
    }

    private static void printAC(ACGen ac) {
        String p = ac.getParent() == null ? "null" : "ac" + ac.getParent().getIndex();
        if (ac.getChildren().size() > 0) {
            System.out.println(String.format("AccountClassification ac%1$s = createAC(%4$s, \"%2$s\", \"%3$s\");", ac.getIndex(), getNameStr(ac.getName()), ac.getCode(), p));
            for (ACGen acc : ac.getChildren()) {
                printAC(acc);
            }
        } else {
            System.out.println(String.format("createAC(%4$s, \"%2$s\", \"%3$s\");", index, getNameStr(ac.getName()), ac.getCode(), p));
        }
    }

    private static String getNameStr(String name) {
        StringBuilder sb = new StringBuilder();
        for (char c : name.toCharArray()) {
            sb.append(formatChar(c));
        }
        return sb.toString();
    }
    
    private static class ACGen {
        AccountClassification ac;
        ACGen parent = null;
        Set<ACGen> children = new HashSet<ACGen>();
        private int index;

        private ACGen(AccountClassification ac) {
            this.ac = ac;
        }

        public AccountClassification getAc() {
            return ac;
        }

        public void setAc(AccountClassification ac) {
            this.ac = ac;
        }

        public ACGen getParent() {
            return parent;
        }

        public void setParent(ACGen parent) {
            this.parent = parent;
        }

        public Set<ACGen> getChildren() {
            return children;
        }

        public void setChildren(Set<ACGen> children) {
            this.children = children;
        }
        
        public void addChild(ACGen acg) {
            getChildren().add(acg);
            acg.setParent(this);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return ac.getName();
        }

        public String getCode() {
            return ac.getCode();
        }
        
    }

}
