#
# Defining the structs that are stored in Cassandra
#
# !!! ENSURE YOU UPDATE THE VERSION NUMBER WHEN UPDATING !!!
namespace java cassie

# See the Semantic Versioning Specification (SemVer) http://semver.org.
const string VERSION = "0.0.1"

#
# Structs (only structs in here -- just being used to serialize in Cassandra)
#

/** A token and its weight
 * @param t, the token itself
 * @param w, the weight we use for the token, most likely tf-idf based
 */
struct Token {
    1: required string t,
    2: required double w,
}

/** A Post vector
 * @param vec, the vector of tokens
 */
struct PostVector {
    1: required list<Token> vec,
    2: required i32 time,
}
