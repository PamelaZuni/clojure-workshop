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
`x`,
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
(clojure.string/replace "Hello World2" #"w" (fn [letter] (do (println letter "!"))) )
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
 "David Bowie" {
                "The Man Who Mapped the World" {:year 1970, :duration "4:01"}
                "Comma Oddity" {:year 1969, :duration "5:19"}
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

(assoc favorite-fruit :yearly_production_in_tonnes {:china 2025000 :italy
                                                    541000 :new_zealand 412000 :iran 311000 :chile 225000})
{:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy",
 :yearly_production_in_tonnes {:china 2025000, :italy 541000, :new_zealand 412000,
                               :iran 311000, :chile 225000}}
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


