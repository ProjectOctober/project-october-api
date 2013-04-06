#
# Interface definition for October recommendation service
#
# !!! ENSURE YOU UPDATE THE VERSION NUMBER WHEN UPDATING !!!
namespace java october
namespace rb Backend

# See the Semantic Versioning Specification (SemVer) http://semver.org.
const string VERSION = "0.2.0"

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

/** A user id from the frontend
 * @param id, the id the frontend uses for a user
 */
struct User {
    1: required i64 user_id,
}

/** A pair of token and frequency
 * @param t, the token itself
 * @param f, the count of the frequency of the token
 */
struct Token {
    1: required string t,
    2: required i32 f,
}

/** The types of actions that can be performed in a triple (subject, verb, object) */
enum Action {
    READ,
    VOTE_UP,
    VOTE_DOWN,
    VOTE_UP_NEGATE,
    VOTE_DOWN_NEGATE,
    POST,
    COMMENT,
    REPORT,
    TAG
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

    /** Test for connectivity */
    string ping() throws (1: TimeoutException te),

    /** Request a list of posts that are most appropriate for a user
     * @param user_id, the user that the posts are being requested for
     */
    PostList recPosts(1: required i64 user_id) throws (1: NotFoundException nfe, 2: EngineException ee, 3: TimeoutException te),

    /** Informs the backend that a new user has been created
     * @param user_id, the user that is being added
     */
    bool addUser(1: required i64 user_id) throws (1: EngineException ee, 2: TimeoutException te),

    /** Informs the backedn that a user has submitted a post
     * @param user_id, the user that submitted the post (if < 0, this is not submitted by a user)
     * @param post_id, the post the user submitted
     * @param raw_freq, a list of <token, freq> pairs that correspond to the number of times a keyword is in a post.
     */
    bool addPost(1: required i64 user_id, 2: required i64 post_id, 3: required list<Token> raw_freq) throws (1: EngineException ee, 2: TimeoutException te, 3: NotFoundException nfe),

    /* TODO: Consider deprecating the x_v_y functions below in favor of explicit functions. */

    /** Alert the recommender that a user has actioned a post
     * @param user_id, the user that performed the action
     * @param verb, the action taken (this is from the Action enum)
     * @param post_id, the post that the action is being performed on
     */
    bool user_v_post(1: required i64 user_id, 2: required Action verb, 3: required i64 post_id) throws (1: NotFoundException nfe),

    /** Alert the recommender that a user has actioned a comment
     * @param user_id, the user that performed the action
     * @param verb, the action taken (this is from the Action enum)
     * @param comment_id, the comment that the action is being performed on
     */
    bool user_v_comment(1: required i64 user_id, 2: required Action verb, 3: required i64 comment_id) throws (1: NotFoundException nfe),

    /** Return the list of top n tokens for a user
     * @param user_id, the user to query for
     * @param limit, the maximum amount of tokens to return 
     */
    map<string, i64> user_top_terms(1: required i64 user_id, 2: required i32 limit) throws (1: NotFoundException nfe),

    /** Return a list of documents in sorted order of relevance for a search query
     * @param query, a map of tokens to their weight
     */
    map<i64, double> text_search(1: required list<string> tokens) throws (1: EngineException ee),
}
