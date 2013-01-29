#
# Interface definition for October recommendation service
#
# !!! ENSURE YOU UPDATE THE VERSION NUMBER WHEN UPDATING !!!

namespace rb Recommender
namespace scala Display

# See the Semantic Versioning Specification (SemVer) http://semver.org.
const string VERSION = "0.0.1"

#
# Structs
#

/** A single post with its calculated weight.
 * @param post_id, the unique id of a post.
 * @param weight, the "importance" of the post to the querying user [0,1].
 */
struct Post {
    1: required i64 post_id,
    2: optional double weight,
}

/** A list of posts along with a confidence for the accuracy of the list.
 * @param confidence, the confidence in the results.
 * @param posts, a list of Posts.
 */
struct PostList {
    1: optional double confidence,
    2: required list<Post> posts,
}

#
# Exceptions
#

/** The queried object does not exist. */
exception NotFoundException {
}

/** There was an error processing the request */
exception EngineException {
    1: required string why,
}

/** Request took too long to process */
exception TimeoutException {
}

#
# Service
#

service Recommender {
    string ping() throws (TimeoutException te),
    PostList recPosts(1: required i64 user_id) throws (NotFoundException nfe, EngineException ee, TimeoutException te),
}
