package com.atguigu;

import java.util.*;

public class Demo {

    public boolean checkXMatrix(int[][] grid) {
        boolean flag = true;
        int length = grid.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j || (i + j) == (length - 1)) {
                    if (grid[i][j] != 0) {
                        flag = false;
                        break;
                    }
                } else {
                    if (grid[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        char[] s = haystack.toCharArray(), p = needle.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }

    public String decodeMessage(String key, String message) {
        char cur = 'a';
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c != ' ' && !map.containsKey(c)) {
                map.put(c, cur);
                cur++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                res.append(' ');
            } else {
                res.append(map.get(message.charAt(i)));
            }
        }
        return res.toString();
    }

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        long res = 0;
        long[] arr = new long[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - delay; j >= 0 && j > i - forget; j--) {
                arr[i] = (arr[i] + arr[j]) % 10000000007L;
            }
        }
        for (int i = n - forget; i < n; i++) {
            res = (arr[i] + res) % 10000000007L;
        }
        return (int) res;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[0] == 0 && i <= k) {
                return true;
            } else if (map.containsKey(nums[i] - nums[0]) && (i - map.get(nums[i] - nums[0])) <= k) {
                return true;
            } else {
                map.put(nums[i] - nums[0], i);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff == 0) {
            return false;
        }
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j <= length && j <= i + indexDiff; j++) {
                if (Math.abs(nums[j] - nums[i]) <= valueDiff) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; ++i) {
            int pre = ans.get(ans.size() - 1).length();
            if (!(pre < folder[i].length() && ans.get(ans.size() - 1).equals(folder[i].substring(0, pre)) && folder[i].charAt(pre) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }

    public static long maximumSubsequenceCount(String text, String pattern) {
        long a = 0, b = 0, res = 0;
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.charAt(i) == pattern.charAt(1)) {
                b++;
            } else if (text.charAt(i) == pattern.charAt(0)) {
                res += b;
                a++;
            }
        }
        if (pattern.charAt(0) == pattern.charAt(1)) {
            return (b + 1) * b / 2;
        }
        return res + Math.max(a, b);
    }

    public static void main(String[] args) {
        System.out.println(maximumSubsequenceCount("iekbksdsmuuzwxbpmcngsfkjvpzuknqguzvzik", "mp"));
    }
}
