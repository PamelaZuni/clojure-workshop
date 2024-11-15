(ns clojure-workshop.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(inc 10)
;The returned value is 11, which is indeed 10 + 1.


Functions are invoked according to the following structure:
; (operator operand-1 operand-2 operand-3 …)
; for example:
;user=> (* 2 3 4)
;24
;
;
;The list, denoted by opening and closing parenthesis, (), is evaluated to a function
;call (or invocation).
;• When evaluated, the * symbol resolves to the function that implements
;the multiplication.
;• 2, 3, and 4 are evaluated to themselves and passed as arguments to the function.

;
;Add 1, 2, and 3 and multiply the result by 10 minus 3, which corresponds to the
;following infix notation: (1 + 2 + 3) * (10 - 3). You should obtain the following result:
(* (+ 1 2 3) (- 10 3))

'(1 2 3)
;
;=> ("a" "b" "c" "d")


;• when, which can be used when we are only interested in the case of a condition
;being truthy (a value is truthy when considered true in the context of a
;                Boolean expression).
;• do, which can be used to execute a series of expressions and return the value of the
;last expression.
;• def and let, which are special forms that are used to create global and
;local bindings.
;• fn and defn, which are special forms that are used to create functions.


;If, do, and when . We will write expressions that contain multiple statements, as well as conditional expressions.

(if true "Yes" "No")
;=> "Yes"
(if false (+ 3 4) (rand))
;=> 0.6885012818537077

(if false "yes" "no")
;=> "no"


(if true (do (println "Calculating a random number...") (rand)) (+ 1
                                                                   2)) ;Calculating a random number...
;=> 0.32885450264145855

(if false (do (println "Calculating a random number...") (rand)) (+ 1 2))
;=> 3

; With do Only the value of the last one is returned
(do (* 3 4) (/ 8 4) (+ 1 1))
; 2

;We can combine the use of if and do to execute multiple operations in a
;conditional branching:
(if true (do (println "Calculating a random number...") (rand)) (+ 1 2))
;=>Calculating a random number...
;=> 0.8039569288235769

(if false (do (println "Calculating a random number...") (rand)) (+ 1 2))
;=> 3

(if true (do (println "Calculating a random number...") (rand)))
;Calculating a random number...
;=> 0.8731490735227142

(if false (do (println "Not going to happen")))
;Nil
;Because the third argument does not exist

(if true
  (do (+ 3 5)))
;=> 8

(if false
  (do (+
        3 5)))
;=> nil


;The when operator, instead of combining ig and do, when you are only interested in doing work in one branch of the conditional execution, use when.
;When is used only for TRUE, does not have false. If it has false will be returning nill
;When also returns only the last argument
(when true (println "First argument") (println "Second argument") "An the last is returned")

(when false (println "First argument") (println "Second argument") "An the last is returned")


;BINDINGS that are the variables global (def) and  local (let)

(def x 20)
;20

(do (def x 42) (+ 10 20))
;30

(do (def x 42))
;42 (why it returned just with one argument?)


;Let takes a vector as a parameter to create the local bindings, and then a series of expressions
;that will be evaluated like they are in a do block.
;Note: Vector is similar to a list, in the sense that they both are a sequencial collection of values.
;Their underlying data structure is different.

(let [y 3] (println y) (* 10 y))
3
;=> 30

(let [x 10] (* 20 x))
;=> 200

(let [y 3] (println y) (* 10 y))
;3
;=> 30

(let [hello "welcome"] (str "You are " hello))

(let [x 10 y 20] (str "x is " x " and y is " y))
"x is 10 and y is 20"


(def message "Let's add them all!")

(let [x (* 10 3)
      y 20
      z 100]
  (println message)
  (+ x y z))
;Let's add them all!
;=> 150

;Creating Simple Functions with fn and defn

;The special form that's used to define functions is fn. Let's jump right into it by creating
;our first function:


;Create a function that takes a parameter named x and return its square value (multiply it by itself):

(fn [x] (* x x))

((fn [x] (* x x)) 2)
; 4

;If we wanted our function to be reusable or testable, it would be better for it to have a name.

(def square (fn [x] (* x x)))
;named the function to reuse
(square 6)
;=> 36

;The pattern of combining def and fn is so common that a built-in macro was born out of necessity:
;defn.
(defn square [x] (* x x))
(square 10)
;=> 100


(defn meditate [s calm]
  (println "Clojure Medidate v1.0")
  (if calm
    (clojure.string/capitalize s)
    (str (clojure.string/upper-case s) "!")))

(meditate "Bubu" true)
;Clojure Medidate v1.0
;=> "Bubu"

(meditate "Bubu" false)
;Clojure Medidate v1.0
;=> "BUBU!"

(meditate "in calmness lies true pleasure" true)
Clojure Medidate v1.0
;=> "In calmness lies true pleasure"
(meditate "in calmness lies true pleasure" false)
Clojure Meditate v1.0
;=> "IN CALMNESS LIES TRUE PLEASURE!"

;The doc-string parameter it will allow you to add a description of your function.
;The doc-string is not only useful when browsing a project's source code – it also
;It is good practice to document the arguments with backticks, `, like we did with
`x `,
        (defn square
          "Returns the product of the number `x` with itself"
          [x]
          (* x x))

;The doc-string is not only useful when browsing a projects source code -
; it also makes it available to the doc function.
(doc square)
;Returns the product of the number `x` with itself
;=> nil


(defn c02-estimate
  "Returns the estimated level of CO2 ppm for that year"
  [year]
  (+ 382 (
          (let [year-diff (year - 2006)])
          * 2)))

;--------------------------------------------------------

;;Exercise 1
;Create a function called co2-estimate that takes one integer parameter called year and
;returns the estimated level of CO2 ppm for that year

(defn c02-estimate0 [year]
  [year]
  (+ 382 (* 2 (let [diff-year (- year 2006)] diff-year))))
; => 470


;Truthiness, nil, and equality
;
;nil is a value that represents the absence of value. It is also often called NULL in other
;programming languages. Representing the absence of value is useful because it means
;that something is missing

;false and nil are the only values that are treated as falsey in Clojure; everything else is
;truthy.

;it only returns true if its the true comparing, because its a boolean
(true? true)
;=>true

;returns true because it is comparing, then its true
(false? false)

;AND and OR
;and returns the first falsey value that it encounters (from left to right) and will
;not evaluate the rest of the expression when that is the case. When all the values
;passed to and are truthy, and will return the last value.

(and "Hello")
;=> "Hello"
(and "Hello" "Then" "Goodbye")
;=> "Goodbye"
(and false "Hello" "Goodbye")
;=> false


;It evaluates the first expression that is true, and after the second that is false returns nil
;and evaluated the first expression, which printed Hello and returned nil, which
;is falsey.
(and (println "Hello") (println "Goodbye"))
;println Hello
;=> nil

;OR

;Or works in a similar fashion: it will return the first truthy value that it comes across and it will not
;evaluate the rest of the expression when that is the case.
;When all the values that are passed to or are falsey, or will return the last value.


;"Hello" is truthy, so or stops immediately and returns "Hello" without evaluating the remaining arguments.
(or "Hello" "Then" "Goodbye")
;=> "Hello"


;The first argument, false, is falsey, so or continues to the next value.
;"Then" is truthy, so or stops and returns "Then" without evaluating "Goodbye".
(or false "Then" "Goodbye")
;=> "Then"

(or (println "Hello") true)
; println Hello
;=> true

;Equality and Comparisons

;Comparing Values

(= 1 1)
;=> true
(= 1 2)
;=> false

(= 1 1 1 -1)
;false

(= nil nil)
;=> true

(= false nil)
;=> false

(= "hello" "hello" (clojure.string/reverse "olleh"))
;=> true

(= [1 2 3] [1 2 3])
;=> true

;Maybe more surprisingly, but sequences of different types can be considered equal
;as well:
(= '(1 2 3) [1 2 3])
;=> true

;The = function can also take one argument, in which case it will always return true:
(= 1)
;=> true
(= "I will not reason and compare: my business is to create")
;=> true

;The other comparison operatoes, that is, >, >=, <, and <=, can only be used with numbers.

;< Returns true if all its arguments are in a strictly increasing order. Try to evaluate the following expressions:

(< 1 2)
;true

(> 1 2)
;false

(< 1 10 100 1000)
;true

(< 1 10 10 100)
;FALSE

(< 3 2 3)
;=> false
(< -1 0)
;=> true
(< -1 -2)
;=> false
(< -1 0 -1)
;=> false
(< -1 0 1 2)
;=> true

;<= is similar, but adjacent arguments can be equal:

(<= 1 10 10 100)
;=> true
(<= 1 1 1)
;=> true
(<= 1 2 3)
;=> true
(<= 1 -1 2)

(<= 1 50 -1)
;=> false
(<= 20 20 30)
;=> true                                                    ;
(<= 20 20 10)
;=> false

; > and >= have a similar behavior and return true when their arguments are in a decreasing order.
; >= allows adjacent arguments to be equal:

(> 3 2 1)
;=> true
(> 3 2 2)
;=> false
(>= 3 2 2)
;=> true
;                                                  ;

;Finally, the not operator is a useful function that returns true when its argument is falsey (nil or false),
;and false otherwise. Let's true an example:

(not true)
;=> false
(not nil)
;=> true
(not (< 1 2))
;=> false
(not (= 1 1))
;=> false

;To put things together, let;s consider the following JavaScript code:

;To put things together, let's consider the following JavaScript code:
;let x = 50;
;if (x >= 1 && x <= 100 || x % 100 == 0) {
;                                         console.log("Valid");
;                                         } else {
;                                                 console.log("Invalid");
;                                                 }

;If we wanted to translate this to Clojure code, we would write the following:

(let [x 50]
  (if (or (<= 1 x 100) (= 0 (mod x 100)))
    (println "Valid")
    (println "Invalid"))
  )
;Valid
;Since we are using or, only one of these conditions needs to be true for the entire condition to be true.
;In this case, Condition 1 is true.

;If we want to use inline if would become the following:

(let [x 50]
  (println (if (or (<= 1 x 100) (= 0 (mod x 100))) "Valid" "Invalid")))
;Valid

(let [x 50]
  (println (if (and (<= 1 x 100) (= 0 (mod x 100))) "Valid" "Invalid")))
;Invalid

;;Activity 1.03: The meditate Function v2.0
;The specifications of the function are
;as follows:
;• calmness-level is a number between 1 and 10, but we will not check the input
;for errors.
;• If the calmness level is strictly inferior to 5, we consider the user to be angry. The
;function should return the s string transformed to uppercase concatenated with
;the string ", I TELL YA!".
;• If the calmness level is between 5 and 9, we consider the user to be calm and
;relaxed. The function should return the s string with only its first letter capitalized.
;• If the calmness level is 10, the user has reached nirvana, and is being possessed by
;the Clojure gods. In its trance, the user channels the incomprehensible lang


(defn meditate2
  [string calmness-level]
  (println "Clojure Meditate v2.0")
  (if (< calmness-level 4)
    (str (clojure.string/upper-case string) ", I TELL YA")
    (if (<= 4 calmness-level 9)
      (clojure.string/capitalize string)
      (if (= 10 calmness-level)
        (clojure.string/reverse string)))))


;Rewrite it only using the COND

(defn meditate2
  [string calmness-level]
  (println "Clojure Meditate v2.0")
  (cond (< calmness-level 4) (str (clojure.string/upper-case string) ", I TELL YA")
        (<= 4 calmness-level 9) (clojure.string/capitalize string) ;why here is 4
        (= 10 calmness-level) (clojure.string/reverse string)))


;Str/Split/rest/butlast


(def name "Pamela Zuni Rodrigues de Queiroz")
;=> #'clojure-workshop.core/name
;name
;=> "Pamela Zuni Rodrigues de Queiroz"
(clojure.string/split name #" ")
;=> ["Pamela" "Zuni" "Rodrigues" "de" "Queiroz"]
(rest (clojure.string/split name #" "))
;=> ("Zuni" "Rodrigues" "de" "Queiroz")
(butlast (rest (clojure.string/split name #" ")))
;=> ("Zuni" "Rodrigues" "de")
(require '[clojure.string :refer [split]])
;=> nil
(def cat "Pablo Oatmeal Lily")
;=> #'clojure-workshop.core/cat
cat
;=> "Pablo Oatmeal Lily"
(split cat #" ")
;=> ["Pablo" "Oatmeal" "Lily"]
(require '[clojure.string :as str])
;=> nil
(str/split cat #" ")
;=> ["Pablo" "Oatmeal" "Lily"]
(str [1 2 3])
"[1 2 3]"
;=> "[1 2 3]"
(str "That's the way you " "con" "ca" "te" "nate")


;Data Types and Immutability


(clojure.string/includes? "potatoes" "carbos")
=> false
(clojure.string/includes? "potatoes" "ta")
=> true

(clojure.string/replace "Hello World" #"\w" "!")
;=> "!!!!! !!!!!"
(clojure.string/replace "Hello World2" #"\w" "!")
;=> "!!!!! !!!!!!"
(clojure.string/replace "Hello World2" #"\d" "!")
;=> "Hello World!"
(clojure.string/replace "Hello World2" #"w" (fn [letter] (do (println letter "!"))))
;=> "Hello World2"
(clojure.string/replace "Hello World2" #"w" (fn [letter] (do (println letter) "!")))
;=> "Hello World2"
(clojure.string/replace "Hello World" #"\w" (fn [letter] (do (println letter) "!")))
;H
;e
;l
;l
;o
;W
;o
;r
;l
;d
;=> "!!!!! !!!!!"
(clojure.string/replace "Hello World" #"\w\w" (fn [letter] (do (println letter) "!")))
;He
;ll
;Wo
;rl
;=> "!!o !!d"
(int \a)
;=> 97
(first (char-array "a"))
;=> \a
(Math/pow (int (first (char-array "a"))) 2)
;=> 9409.0
(defn encode-letter [s]
  (let [code (Math/pow (int (first (char-array s))) 2)]
    (str (int code))))
;=> #'clojure-workshop.core/encode-letter
(encode-letter "a")
;=> "9409"
(defn encode [s]
  (clojure.string/replace s #"\w" encode-letter))
;=> #'clojure-workshop.core/encode
(encode "a")
;=> "9409"
(encode "aa")
;=> "94099409"
(encode "ab")
;=> "94099604"
(encode "b")
;=> "9604"
(int \b)
;=> 98
(* 98 98)
;=> 9604
(encode "Hello World")
;=> "518410201116641166412321 756912321129961166410000"

;Symbols

;Symbols are identifiers referring to something else. We have already been using
;symbols when creating bindings or calling functions. For example, when using def, the
;first argument is a symbol that will refer to a value, and when calling a function such
;as +
;,
;+ is a symbol referring to the function implementing the addition. Consider the
;following examples

(def foo "bar")
;=> #'clojure-workshop.core/foo
foo
;=> "bar"
(defn add-2 [x] (+ x 2))
;=> #'clojure-workshop.core/add-2
(add-2 4)
;=> 6

;Keywords

;You can think of a keyword as some kind of a special constant string. Keywords are
;a nice addition to Clojure because they are lightweight and convenient to use and
;create. You just need to use the colon character, :, at the beginning of a word to
;create a keyword:
;Keywords are typically used as
;keys in a key-value associative map, as we will see in the next topic about collections.


:foo
;=> :foo
:another_keyword
;=> :another_keyword

;Collections

;Clojure is a functional programming language in which we focus on building the
;computations of our programs in terms of the evaluation of functions, rather than
;building custom data types and their associated behaviors.
;The four main data structures for collections that you should
;know about are Maps, Sets, Vectors, and Lists.


;Maps

;A Map is a collection of key-value pairs. Clojure provides – in a persistent and
;immutable fashion – the usual HashMap but also a SortedMap

;HashMaps are called "Hash" because they create a hash of the key and map it to a given
;value. Lookups, as well as other common operations (insert and delete), are fast.

;HashMaps are used a lot in Clojure, notably, for representing entities where we need
;to associate some attributes to some values.

;You can create a HashMap with the literal notation using curly braces. Here is a Map
;with three key-value pairs, with the keys being the :artist, :song, and :year keywords:

{:artist "David Bowie" :song "The Man Who Mapped the World" :year 1970}

;Notice that the values can be of any type, and not only simple values such as strings
;and numbers, but also vectors and even other maps, allowing you to create nested data
;structures and structure information as follows

{
 "David Bowie"       {
                      "The Man Who Mapped the World" {:year 1970, :duration "4:01"}
                      "Comma Oddity"                 {:year 1969, :duration "5:19"}
                      }
 "Crosby Sills Hash" {
                      "Helplessly Mapping" {:year 1969, :duration "2:38"}
                      "Almost Cut My hair" {:year 1979, :duration "4:29", :featuring ["Neil Young", "Rich Hickey"]}
                      }
 }



;=> {"David Bowie" {"The Man Who Mapped the World" {:year 1970, :duration "4:01"},
;                "Comma Oddity" {:year 1969, :duration "5:19"}},
; "Crosby Sills Hash" {"Helplessly Mapping" {:year 1969, :duration "2:38"},
;                      "Almost Cut My hair" {:year 1979, :duration "4:29", :featuring ["Neil Young" "Rich Hickey"]}}}


;Keys can be of different types too, so you could have strings, numbers, or even other
;types as a key; however, we generally use keywords.


;Another way of creating a map is by using the hash-map function, passing in pairs of
;arguments as follows:
(hash-map :a 1 :b 2 :c 3)
;=> {:c 3, :b 2, :a 1}

{:name "Lucy" :age 32 :number-of-teeth 32}


;Exercise 2.02: Using Maps

;In this exercise, we will learn how to access and modify simple maps:

(def favorite-fruit {:name "Kiwi", :color "Green", :kcal_per_100g 61 :distinguish_mark "Hairy"})
favorite-fruit
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy"}

;2. You can read an entry from the map with the get function

(get favorite-fruit :name)
;=> "Kiwi"

(get favorite-fruit :color)
;=> "Green"

;If the value for a given key cannot be found, get returns nil,
; but you can specify a fallback value with a third argument to get:

(get favorite-fruit :taste "Very good 8/10")
;=> "Very good 8/10"

(get favorite-fruit :kcal_per_100g 0)
;=> 61

;Maps and keywords have the special ability to be used as functions. When positioned in the "operator position"
;(as the first item of the list), they are invoked as a function that can be used to look up a value in a map.

(favorite-fruit :color)
;=> "Green"

(:color favorite-fruit)
;=> "Green"

;As with the get function, those ways of retrieving a value return nil when the key
;cannot be found, and you can pass an extra argument to provide a fallback value.

(:shape favorite-fruit "egg-like")
;=> "egg-like

;We would like to store this value in the map.
; Use assoc to associate a new key, :shape, with a new value, "egg-like", in our map:

(assoc favorite-fruit :shape "egg-like")
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy", :shape "egg-like"}

;/Change the color of favorite-fruit by associating a new value to the :color key:
(assoc favorite-fruit :color "Brown")
;=> {:name "Kiwi", :color "Brown", :kcal_per_100g 61, :distinguish_mark "Hairy"}

;assoc replaces the existing value when a key already exists, because HashMaps
;cannot have duplicate keys.

;10. If we wanted to add more structured information, we could add a map as a value.
;Add production information as a nested map in our Kiwi map:

(assoc favorite-fruit :yearly_production_in_tonnes {:china              2025000 :italy
                                                    541000 :new_zealand 412000 :iran 311000 :chile 225000})
{:name                        "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy",
 :yearly_production_in_tonnes {:china 2025000, :italy 541000, :new_zealand 412000,
                               :iran  311000, :chile 225000}}
;{:name "Kiwi",
; :color "Green",
; :kcal_per_100g 61,
; :distinguish_mark "Hairy",
; :yearly_production_in_tonnes {:china 2025000, :italy 541000, :new_zealand 412000, :iran 311000, :chile 225000}}

;Having nested maps or other data types is commonly used to represent structured information.

;Decrement kcal_perf_100g with the assoc function, as follows:

(assoc favorite-fruit :kcal_per_100g (- (:kcal_per_100g favorite-fruit) 1))
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}


;12. Decrement kcal_per_100g with the update function and dec, as follows:
;Notice how the value of :kcal_per_100g changed from 61 to 60.
(update favorite-fruit :kcal_per_100g dec)
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}

;You can also pass arguments to the function provided to update; for example
;If we wanted to lower :kcal_per_100g by 10 instead of 1, we could use the subtract function, - , and write the following:

(update favorite-fruit :kcal_per_100g - 10)
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 51, :distinguish_mark "Hairy"}

(update favorite-fruit :kcal_per_100g minus 10)
;=> {:name "Kiwi", :color "Green", :kcal_per_100g 51, :distinguish_mark "Hairy"}


(defn name [hey]
  (+ 5 hey))

(dissoc favorite-fruit :distinguish_mark)
; {:name "Kiwi", :color "Green", :kcal_per_100g 61}

(dissoc favorite-fruit :kcal_per_100g :color)
;=> {:name "Kiwi", :distinguish_mark "Hairy"}


;Sets
;A set is a collection of unique values. Clojure provides HashSet and SortedSet.
; Hash Sets are implemented as Hash Maps, with the key and the value of each entry being identical.

;Hash Sets are fairly common in Clojure and have a literal notation of  hash with curly braces, #{}, for example:

#{1 2 3 4 5}
; => #{1 2 3 4 5}

;As with maps, sets cannot have duplicate entries:
#{:a :a :b :c}

(hash-set :a :b :c :d)
; #{:c :b :d :a}
(set [:a :b :c])
; #{:c :b :a}
(set ["No" "Copy" "Cats" "Please"])
; #{"Copy" "Please" "Cats" "No"}
(sorted-set "No" "Copy" "Cats" "Cats" "Please")
; #{"Cats" "Copy" "No" "Please"}

;Exercise 2.03: Using Sets

(def supported-currencies #{"Dollar" "Japanese yen" "Euro" "Indian rupee" "British pound"})
supported-currencies
;=> #{"Japanese yen" "Indian rupee" "Euro" "Dollar" "British pound"}
(get supported-currencies "Dollar")
;=> "Dollar"
(get supported-currencies "Swiss franc")
;=> nil
(contains? supported-currencies "Dollar")
;=> true
(contains? supported-currencies "Swiss franc")
;=> false

;To add an entry to a set, use the conj function, as in "conjoin"

(conj supported-currencies "Monopoly Money")
;=> #{"Japanese yen" "Indian rupee" "Euro" "Dollar" "Monopoly Money" "British pound"}

(conj supported-currencies "Monopoly Money" "Gold dragon" "Gil")

(conj supported-currencies "Monopoly Money" "Gold dragon" "Gil")
;=> #{"Gold dragon" "Japanese yen" "Indian rupee" "Euro" "Dollar" "Monopoly Money" "British pound" "Gil"}

;Remove one or more items with the disj function, as in "disjoin":

(disj supported-currencies "Dollar" "British pound")
;=> #{"Japanese yen" "Indian rupee" "Euro"}

;Vectors
;A vector is another type of collection that is widely used in Clojure. You can think of vectors as powerful immutable arrays.
;They are collections of values efficiently accessible by their integer index (starting from 0)m and they maintain the order of item insertion as well as duplicates.
;Use a vector when you need to store and read elements in order, and when you don't mind duplicate elements.

[1 2 3]
;[1 2 3]

;Vectors can also be created with the vector function followed by a list of items
;as arguments:
(vector 10 15 2 15 0)
;=> [10 15 2 15 0]


;You can create a vector from another collection using the vec function; for example,
;the following expression converts a Hash Set to a vector

(vec #{1 2 3})
;=> [1 3 2]

;As with other collections, vectors also can contain different types of values:

[nil :keyword "String" {:answers [:yep :nope]}]
;=> [nil :keyword "String" {:answers [:yep :nope]}]

;Exercise 2.04: Using Vectors

(get [:a :b :c] 0)
;=> :a

(get [:a :b :c] 2)
;=> :c

;Let's bind a vector to a symbol to make the practice more convenient:

(def fibonacci [0 1 1 2 3 5 8])
fibonacci
;=> [0 1 1 2 3 5 8]

(get fibonacci 6)
;=> 8

(fibonacci 6)
;=> 8

(conj fibonacci 13 21)
;=> [0 1 1 2 3 5 8 13 21]

;Data types and Immutability
;Each item in the Fibonacci sequence corresponds to the sum of the previous two


;In the preceding example, we used let to create three local bindings and improve the readability.
(let [size (count fibonacci)
      last-number (last fibonacci)
      second-to-last-number (fibonacci (- size 2))]
  (conj fibonacci (+ last-number second-to-last-number)))
;[0 1 1 2 3 5 8 13]

;Lists
;Lists are sequential collections, similar to vectors, but items are added to the front
'(1 2 3)
;=> (1 2 3)

(+ 1 2 3)
;=> 6

'(+ 1 2 3)
;=> (+ 1 2 3)

;Lists can also be created with list function:
(list :a :b :c)
;=> (:a :b :c)

;To read the first element of a list, use first:
(first '(:a :b :c :d))
;=> :a

(rest '(:a :b :c :d))
;=> (:b :c :d)

;You cannot use the get function with a list to retrieve by index. You could use nth, but it
;is not efficient as the list is iterated or "walked" until it reaches the desired position:

(nth '(:a :b :c :d) 2)
; => :c

;Exercise 2.05: Using Lists
;In this exercise, we will practice using lists by reading and adding elements to
;a to-do list.

(def my-todo (list "Feed the cat" "Clean the bathroom" "Save the world")
  )
my-todo
;=> ("Feed the cat" "Clean the bathroom" "Save the world")

;You can add items to your list by using the cons function, which operates
;on sequences:

(cons "Go to work" my-todo)
;=> ("Go to work" "Feed the cat" "Clean the bathroom" "Save the world")

;Similarly, you can use the conj function, which is used because a list is a collection:
(conj my-todo "Go to work")
("Go to work" "Feed the cat" "Clean the bathroom" "Save the world")


;You can add items to your list by using the cons function, which operates
(cons "Go to work" my-todo)
;=> ("Go to work" "Feed the cat" "Clean the bathroom" "Save the world")

(conj my-todo "My job" "Go to work" "Wash my socks")
;=> ("Wash my socks" "Go to work" "My job" "Feed the cat" "Clean the bathroom" "Save the world")

(first my-todo)
;=> "Feed the cat"

(rest my-todo)
;=> ("Clean the bathroom" "Save the world")

(nth my-todo 2)
;=> "Save the world"

;Collection and Sequence Abstractions
;Clojure's data structures are implemented in terms of powerful abstractions. You might
;have noticed that the operations we used on collections are often similar, but behave
;differently based on the type of the collection. For instance, get retrieves items from
;a map with a key, but from a vector with an index; conj adds elements to a vector at the
;back, but to a list at the front.
;A sequence is a collection of elements in a particular order, where each item follows another.
;Maps, sets, vectors, and lists are all collections, but only vectros and lists are sequences, although we can easily obtain a sequence from a map or a set.


;Convert a map into a sequence using the seq function:
(seq language)
;=> ([:name "Clojure"] [:creator "Rich Hickey"] [:platforms ["Java" "JavaScript" ".NET"]])


(seq language)
;=> ([:name "Clojure"] [:creator "Rich Hickey"] [:platforms ["Java" "JavaScript" ".NET"]])
(nth (seq language) 1)

;=> [:creator "Rich Hickey"]
language
;=> {:name "Clojure", :creator "Rich Hickey", :platforms ["Java" "JavaScript" ".NET"]}

;A lot of functions just work on collections directly because they can be turned into
;a sequence, so you could omit the seq step and, for example, call first, rest, or last
;directly on a map or a set:
(first #{:a :b :c})
;:c
(rest #{:a :b :c})
;(:b :a)
;user=> (last language)
[:platforms ["Java" "JavaScript" ".NET"]]

;Data Types and Immutability

;INTO
;into is another useful operator that puts elements of one collection into another
;collection. The first argument for into is the target collection:

(into [1 2 3 4] #{5 6 7 8})
;=> [1 2 3 4 7 6 5 8]


;The resulting vector is not in ascending order because Hash Sets are not sorted:
(into #{1 2 3 4} [5 6 7 8])
;=> #{7 1 4 6 3 2 5 8}

(into #{1 2 3 4} [5 6 7 8])
;=> #{7 1 4 6 3 2 5 8}

;A usage example would be, for example, to deduplicate a vector, just put it into a set:
(into #{} [1 2 3 3 3 4])
;=> #{1 4 3 2}

;To put items into a map, you would need to pass a collection of tuples representing key-value pairs:
(into {} [[:a 1] [:b 2] [:c 3]])
;=> {:a 1, :b 2, :c 3}

;Each item is "conjoined" in the collection, and so it follows the semantic of the target collection
;for inserting items with conj. Elements are added to a list at the front:
(into '() [1 2 3 4])
;=> (4 3 2 1)

; (into '() [1 2 3 4]), here is step-by-step representation of what happened:
(conj '() 1)
;=> (1)

(conj '(1) 2)
;=> (2 1)

(conj '(2 1) 3)
;=> (3 2 1)

(conj '(3 2 1) 4)
;=> (4 3 2 1)

(conj '(3 2 1) 4)
;=> (4 3 2 1)

;If you want to concatenate collections, concat might be more appropriate than into.

(concat '(1 2) '(3 4))
;=> (1 2 3 4)

(into '(1 2) '(3 4))
;=> (4 3 1 2)

;A lot of Clojure functions that operate on sequences will return sequence no matter
;what the input type was concat is one example:

(concat #{1 2 3} #{1 2 3 4})
;=> (1 3 2 1 4 3 2)

(concat {:a 1} ["Hello"])
;=> ([:a 1] "Hello")

;Sort
;Sort can rearrange a collection to order its elements. It has the benefit of being slightly more obvious in terms of
;why you would want a sequence as a result:

(def alphabet #{:a :b :c :d :e :f})
;=> #'clojure-workshop.core/alphabet
alphabet
;=> #{:e :c :b :d :f :a}
(sort alphabet)
;=> (:a :b :c :d :e :f)

(sort [3 7 5 1 9])
;=> (1 3 5 7 9)

;If is needed to have a vector as a result, could use the into function:
(sort [3 7 5 1 9])
;=> (1 3 5 7 9)
(into [] *1)
;=> [1 3 5 7 9]
(def numbers (sort [3 4 5 1]))
;=> (1 3 4 5)
(into [] numbers)
;=> [1 3 4 5]

;Conj can also be used on maps. For its arguments to be consistent with other types
;of collections, the new entry is represented by a tuple:

(conj language [:created 2007])
;language
;=> {:name "Clojure", :creator "Rich Hickey", :platforms ["Java" "JavaScript" ".NET"], :created 2007}

;Similarly, a vector is an associative collection of key-value paris where the key is the index of the value:
(assoc [:a :b :c :d] 2 :z)
;=> [:a :b :z :d]

;Exercise 2.06: Working with nested Data Structures
;A pure function is a function where the return value is only determined by its input values.
;A pure function does not have any side effects, which means that is does not mutate a program's state nor generate any kind of I/O.

;1. Open up a REPL and create the following Hash Map representing the sample gemstone database:

(def gemstone-store-db {
                        :ruby {
                               :name       "Ruby"
                               :stock      480
                               :sales      [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
                               :properties {
                                            :dispersion       0.018
                                            :hardness         9.0
                                            :refractive-index [1.77 1.78]
                                            :color            "Red"
                                            }
                               }
                        })


{:ruby {:name       "Ruby",
        :stock      480,
        :sales      [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
        :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Red"}}}

;One of the most popular questions the shop gets from its customers is about the
;durability of a gem. This can be found in the properties of a gem, at the :hardness
;key. The first function that we need to develop is durability, which retrieves the
;hardness of a given gem.

;2. Use the fet function with the :ruby gem as an example
(get (get (get gemstone-store-db :ruby) :properties) :hardness)
;=> 9.0

;3. Use the keyword as a function to see how it improves the readability of our code:
(:hardness (:properties (:ruby gemstone-store-db)))
;=> 9.0

;Still there is a better way, for example use the get-in function.
;It takes a vector of keys as parameters and digs in the map with just one function call.

;4. Use the get-in function with the [:ruby :properties :hardness] vector of parameters to retrieve the deeply
;nested :hardness key

(get-in gemstone-store-db [:ruby :properties :hardness])
;=> 9.0

;5. Create the durability function that takes the database and the gem keyword as a parameter
;and returns the value of the hardness property:

(defn durability [db gemstone]
  (get-in db [gemstone :properties :hardness]))

(durability gemstone-store-db :ruby)
;=> 9.0

;7. Write the code to change the color property of a gem. Use assoc:
(defn gemstone-color [db gemstone]
  (assoc db [gemstone :properties :color] "Red"))


(assoc (:ruby gemstone-store-db) :properties {:color "Near colorless through pink through all shades of red to a deep crimson"})
;{:name       "Ruby",
; :stock      480,
; :sales      [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
; :properties {:color "Near colorless through pink through all shades of red to a deep crimson"}}


;8. Use into function to takes a collection and put its values in another collection

(into {:a 1 :b 2} {:c 3})
;{:a 1 :b 2 :c 3}

;If we use the update function combined with into, we could obtain the desired result.
(update (:ruby gemstone-store-db) :properties into {:color "Near colorless pink"})
;=>
{:name "Ruby",
 :stock 480,
 :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
 :properties {:dispersion 0.018,
              :hardness 9.0,
              :refractive-index [1.77 1.78],
              :color "Near colorless through oink through all shades or red to a deep crimson"}}
;How the into is in that position line 1125 after the properties?

;The combination of update and into is not very readable or easy to understand.
;Second, we wanted to return the entire database, but it has returned only the "Ruby" entru.
;As with get-in, Clojure offers a simpler way of dealing with nested maps: assoc-in and update-in.
;They work like assoc and update, but take a vector of keus (such as get-in) as a parameter, instead of a single key.
;Use update-in when it is needed to update a deeply nested value with a function
; (for example, to compute the new value with the previous value) use assoc-in
;Use assoc-in to change the color property of the ruby gem:


;WHY ON THE ASSOC IN IT USES LIKE A VECTOR [] INSTEAD OF MAP {}?
(assoc-in gemstone-store-db [:ruby :properties :color] "Near colorless through pink")

;=>
{:ruby {:name "Ruby",
        :stock 480,
        :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
        :properties {:dispersion 0.018,
                     :hardness 9.0,
                     :refractive-index [1.77 1.78],
                     :color "Near colorless through pink\nthrough all shades of red to a deep crimson"}}}



;WHY IT IS NOT USED THE PARAMETER DB? WHY IT TAKES THE [GEMSTONE :PROPERTIES :COLOR] INSIDE THE VECTOR instead of a map?
(defn change-color [db gemstone new-color]
  (assoc-in db [gemstone :properties :color] new-color))

(change-color gemstone-store-db :ruby "Some kind of red")
;=>
{:ruby {:name "Ruby",
        :stock 480,
        :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
        :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Some kind of red"}}}



;13. We can use the update-in function in combination with dec to decrement (decrease by one) the stock.
(update-in gemstone-store-db [:ruby :stock] dec)
;=>
{:ruby {:name "Ruby",
        :stock 479,
        :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712],
        :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Red"}}}

;Creating a Simple in-Memory Database
;Create a couple of helper functions to help maintain the state of the database in memory:



;As guidance, we would like the data structure to have this shape:

{:table-1 {:data [] :indexes {}} :table-2 {:data [] :indexes {}}}

{
 :clients {
           :data [{:id 1 :name "Bob" :age 30} {:id 2 :name "Alice" :age 24}]
           :indexes {:id {1 0, 2 1}}
           },
 :fruits {
          :data [{:name "Lemon" :stock 10} {:name "Coconut" :stock 3}]
          :indexes {:name {"Lemon" 0, "Coconut" 1}}
          },
 :purchases {
             :data [{:id 1 :user-id 1 :item "Coconut"} {:id 1 :user-id 2 :item "Lemon"}]
             :indexes {:id {1 0, 2 1}}
             }
 }

;Storing data and indexes separately allows multiple indexes to be created without having to duplicate the actual data.

;The indexes map stores an association between the index key and its position in the
;data vector for each index key. In the fruits table, "Lemon" is the first record of the data
;vector, so the value in the :name index is 0.

(def memory-db (atom {}))

(defn read-db [] @memory-db)

(defn write-db [new-db] (reset! memory-db new-db))

(defn create-table [table-name]
  )


;Functions in Depth
;Destructuring
;Destructuring allows you to remove data elements from their structure or disassemble a structure.
;It is a technique that improves the readability and conciseness of your code by providing a better tool for a widely used pattern.
;There are two main ways of destructuring data: sequentially (with vectores) and associatively (with maps).

;Imagine that we need to write a function that prints a formatted string given
;a tuple of coordinates, for example, the tuple [48.9615, 2.4372].
;We could write the following function:

(defn print-cords [coords]
  (let [lat (first coords)
        lon (last coords)]
    (println (str "Latitude: " lat " - " "Longitute: " lon))))



